package Proyecto.gestorAlmuerzo;

import Proyecto.GestorAlmuerzo.Repository.*;
import proyecto.gestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.*;
import Proyecto.GestorAlmuerzo.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodExpressTests {

    @InjectMocks
    private UserServices userService;
    @InjectMocks
    private AppServices appServices;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServices categoryServices;
    @Mock
    private PlateRepository plateRepository;

    @InjectMocks
    private PlateServices plateServices;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientServices ingredientServices;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServices orderServices;

    @Test
    public void testLoginSuccessful() throws GestorAlmuerzosAppException {
        Role rol = new Role( "client");
        roleRepository.save(rol);
        User user = new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", null,roleRepository);
        when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        boolean result = userService.login("sebassele2008@gmail.com", "1234");
        assertTrue(result);
    }

    @Test
    public void testLoginEmptyEmail() {
        // Test
        try {
            userService.login("", "password");
        } catch (GestorAlmuerzosAppException e) {
            // Verify
            assert(e.getMessage().equals(GestorAlmuerzosAppException.EmptyEmail));
            return;
        }
        assert(false); // The test should throw an exception
    }

    @Test
    public void testGetUser() {
        // Mocking
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(user));

        // Test
        Optional<User> result = userService.getUser("test@example.com");

        // Verify
        assert(result.isPresent());
        assert(result.get().getEmail().equals("test@example.com"));
    }

    @Test
    public void testAddUser() throws GestorAlmuerzosAppException {
        User userToAdd = new User("test@example.com", "John", "Doe", "password", null, roleRepository);
        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(userToAdd));
        userService.addUser(userToAdd,false);
        Optional<User> result = userService.getUser("test@example.com");
        User userAdded = result.get();
        assertEquals("test@example.com", userAdded.getEmail());
        assertEquals("John", userAdded.getNombre());
        assertEquals("Doe", userAdded.getApellido());
        assertEquals("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8", userAdded.getPassword());
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void testDeleteUser() {
        int roleIdToDelete = 1;
        appServices.deleteUser(roleIdToDelete);
        verify(roleRepository, times(1)).deleteById(roleIdToDelete);
    }

    @Test
    public void ShouldNotLoginWithIncorrectInformation()  {
        try {
            User user = new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", null, roleRepository);
            when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
            boolean result = userService.login("sebassele2008@gmail.com", "123456");
            assertFalse(result);
        } catch (GestorAlmuerzosAppException e){
            assertEquals(e.getMessage(),GestorAlmuerzosAppException.IncorrectInformation);
        }
    }

    @Test
   public void ShouldNotLoginWithEmptyPassword()  {
        try {
            User user = new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", null, roleRepository);
            boolean result = userService.login("sebassele2008@gmail.com", "");
            assertFalse(result);
        } catch (GestorAlmuerzosAppException e){
            assertEquals(e.getMessage(),GestorAlmuerzosAppException.EmptyPassword);
        }
    }

    @Test
    void testGetRol() {
        Role expectedRole = new Role( "client");
        int roleId = expectedRole.getId();
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));
        Optional<Role> result = appServices.getRol(roleId);
        assertEquals(Optional.of(expectedRole), result);
    }

    @Test
    void testAddRol() {
        Role roleToAdd = new Role("client");
        appServices.addRol(roleToAdd);
        verify(roleRepository, times(1)).save(roleToAdd);
    }

    @Test
    void testUpdateRole() {
        Role roleToUpdate = new Role("updatedRole");
        when(roleRepository.save(Mockito.any(Role.class))).thenReturn(roleToUpdate);

        Role result = appServices.updateRole(roleToUpdate);

        assertEquals(roleToUpdate, result);
    }

    @Test
    void testGetAllRoles() {
        List<Role> expectedRoles = Collections.singletonList(new Role( "client"));
        when(roleRepository.findAll()).thenReturn(expectedRoles);

        List<Role> result = appServices.getAllRoles();

        assertEquals(expectedRoles, result);
    }

    @Test
    void testGetAllCategories() {
        List<Category> expectedCategories = Collections.singletonList(new Category( "Test Category"));
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> result = categoryServices.getAllCategories();

        assertEquals(expectedCategories, result);
    }

    @Test
    void testGetCategoryById() {
        Integer categoryId = 1;
        Category expectedCategory = new Category("Test Category");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Optional<Category> result = categoryServices.getCategoryById(categoryId);

        assertEquals(Optional.of(expectedCategory), result);
    }

    @Test
    void testAddCategory() {
        Category categoryToAdd = new Category("New Category");

        categoryServices.addCategory(categoryToAdd);

        verify(categoryRepository, times(1)).save(categoryToAdd);
    }

    @Test
    void testGetCategoryByName() {
        String categoryName = "Test Category";
        Category expectedCategory = new Category(categoryName);
        when(categoryRepository.findByName(categoryName)).thenReturn(expectedCategory);

        Category result = categoryServices.getCategoryByName(categoryName);

        assertEquals(expectedCategory, result);
    }

    @Test
    void testGetAllPlates() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Ingredient1"));
        Plate expectedPlate = new Plate(1, "Test Plate", "Description", 10, categories,ingredients, "picture.jpg");
        when(plateRepository.findAll()).thenReturn(Collections.singletonList(expectedPlate));

        List<Plate> result = plateServices.getAllPlates();

        assertEquals(Collections.singletonList(expectedPlate), result);
    }

    @Test
    void testGetPlateById() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Ingredient1"));

        long plateId = 1;
        Plate expectedPlate = new Plate((int) plateId, "Test Plate", "Description", 10, categories,ingredients,"picture.jpg");
        when(plateRepository.findById(plateId)).thenReturn(Optional.of(expectedPlate));

        Optional<Plate> result = plateServices.getPlateById(plateId);

        assertEquals(Optional.of(expectedPlate), result);
    }

    @Test
    void testAddPlate() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Ingredient1"));
        Plate plateToAdd = new Plate(1, "New Plate", "Description", 15, categories,ingredients, "new_picture.jpg");

        when(plateRepository.save(plateToAdd)).thenReturn(plateToAdd);

        Plate result = plateServices.addPlate(plateToAdd);

        assertEquals(plateToAdd, result);
    }

    @Test
    void testUpdatePlate() {
        HashSet<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Ingredient1"));
        Plate plateToUpdate = new Plate(1, "Updated Plate", "Updated Description", 20, categories,ingredients,"updated_picture.jpg");
        plateServices.updatePlate(plateToUpdate);

        verify(plateRepository, times(1)).save(plateToUpdate);
    }

    @Test
    void testDeletePlate() {
        int plateId = 1;
        plateServices.deletePlate(plateId);
        verify(plateRepository, times(1)).deleteById((long) plateId);
    }

    @Test
    void testFindByCategoriesId() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("proofCategory"));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Ingredient1"));
        Plate expectedPlate = new Plate(1, "New Plate", "Description", 15, categories,ingredients, "new_picture.jpg");

        when(plateRepository.findByCategoriesId(1L)).thenReturn(Collections.singletonList(expectedPlate));

        List<Plate> plate = plateServices.findByCategoriesId(1L);
        assertEquals(Collections.singletonList(expectedPlate), plate);
    }

    @Test
    void testGetAllIngredients() {
        Ingredient expectedIngredient = new Ingredient("MyIngredient","IngredientTest",1);
        when(ingredientRepository.findAll()).thenReturn(Collections.singletonList(expectedIngredient));

        List<Ingredient> result = ingredientServices.getAllIngredients();

        assertEquals(Collections.singletonList(expectedIngredient), result);
    }

    @Test
    void testGetFilteredPlates() {
        Ingredient ingredientPrefered = new Ingredient("Should Contain Me");
        Ingredient ingredientBanned = new Ingredient("Should Not Contain Me");
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("proofCategory"));
        Set<Ingredient> preferedIngredients = new HashSet<>();
        preferedIngredients.add(ingredientPrefered);
        Set<Ingredient> bannedIngredients = new HashSet<>();
        bannedIngredients.add(ingredientBanned);
        Plate plate1 = new Plate(1, "New Plate", "Description", 15, categories,preferedIngredients, "new_picture.jpg");
        Plate plate2 = new Plate(1, "New Plate", "Description", 15, categories,preferedIngredients, "new_picture.jpg");
        Plate plate3 = new Plate(1, "New Plate", "Description", 15, categories,bannedIngredients, "new_picture.jpg");
        Plate plate4 = new Plate(1, "New Plate", "Description", 15, categories,bannedIngredients, "new_picture.jpg");
        List<Plate> allPlates = new ArrayList<>();
        List<Ingredient> prerered = new ArrayList<>();
        List<Ingredient> banned = new ArrayList<>();
        allPlates.add(plate1);
        allPlates.add(plate2);
        allPlates.add(plate3);
        allPlates.add(plate4);
        prerered.add(ingredientPrefered);
        banned.add(ingredientBanned);
        List<Plate> result = plateServices.getFilteredPlates(allPlates,prerered,banned);
        assertNotNull(result);
        assertTrue(result.contains(plate1));
        assertTrue(result.contains(plate2));
        assertFalse(result.contains(plate3));
        assertFalse(result.contains(plate4));


    }

    @Test
    void testGetAllIngredientsByIds(){
        Ingredient expectedIngredient = new Ingredient("MyIngredient","IngredientTest",2);
        List<Long> ingredientsIds = new ArrayList<>();
        ingredientsIds.add(1L);
        when(ingredientRepository.findAllById(ingredientsIds)).thenReturn(Collections.singletonList(expectedIngredient));
        List<Ingredient> result = ingredientServices.getAllIngredientsByIds(ingredientsIds);
        assertEquals(Collections.singletonList(expectedIngredient),result);
    }

    @Test
    void testGetIngredientById() {
        long ingredientId = 1;
        Ingredient expectedIngredient = new Ingredient((int) ingredientId, "Test Plate", "Description", 10);
        when(ingredientRepository.findById(ingredientId)).thenReturn(Optional.of(expectedIngredient));

        Optional<Ingredient> result = ingredientServices.getIngredientById(ingredientId);

        assertEquals(Optional.of(expectedIngredient), result);
    }

    @Test
    void testGetIngredientByName() {
        String ingredientName = "Test Plate";
        Ingredient expectedIngredient = new Ingredient(1, ingredientName, "Description", 10);
        when(ingredientRepository.findByName(ingredientName)).thenReturn(expectedIngredient);

        Optional<Ingredient> result = Optional.ofNullable(ingredientServices.getIngredientByName(ingredientName));

        assertEquals(Optional.of(expectedIngredient), result);
    }


    @Test
    void testAddIngredient() {
        Ingredient ingredientToAdd = new Ingredient(1, "New Plate", "Description", 15);

        when(ingredientRepository.save(ingredientToAdd)).thenReturn(ingredientToAdd);

        Ingredient result = ingredientServices.addIngredient(ingredientToAdd);

        assertEquals(ingredientToAdd, result);
    }

    @Test
    void testUpdateIngredient() {
        Ingredient ingredientToUpdate = new Ingredient(1, "Updated Plate", "Updated Description", 20);
        ingredientServices.updateIngredient(ingredientToUpdate);

        verify(ingredientRepository, times(1)).save(ingredientToUpdate);
    }

    @Test
    void testDeleteIngredient(){
        long ingredientId = 1;
        ingredientRepository.deleteById(ingredientId);
        verify(ingredientRepository, times(1)).deleteById(ingredientId);
    }

    @Test
    void testGetAllOrders(){
        Order expectedOrder = new Order(1);
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(expectedOrder));

        List<Order> result = orderServices.getAllOrders();

        assertEquals(Collections.singletonList(expectedOrder), result);
    }

    @Test
    void testGetOrderById(){
        int orderId = 1;
        Order expectedOrder = new Order( orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        Optional<Order> result = orderServices.getOrderById(orderId);

        assertEquals(Optional.of(expectedOrder), result);
    }

    @Test
    void TestAddOrder() {
        Order orderToAdd = new Order(1);

        when(orderRepository.save(orderToAdd)).thenReturn(orderToAdd);

        Order result = orderServices.addOrder(orderToAdd);

        assertEquals(orderToAdd, result);
    }

    @Test
    void updateOrder() {
        Order orderToUpdate = new Order(1);
        orderServices.updateOrder(orderToUpdate);

        verify(orderRepository, times(1)).save(orderToUpdate);
    }

}
