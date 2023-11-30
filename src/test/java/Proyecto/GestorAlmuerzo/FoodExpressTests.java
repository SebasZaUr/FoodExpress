package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.Repository.CategoryRepository;
import Proyecto.GestorAlmuerzo.Repository.PlateRepository;
import Proyecto.GestorAlmuerzo.Repository.RoleRepository;
import Proyecto.GestorAlmuerzo.Repository.UserRepository;
import Proyecto.GestorAlmuerzo.controller.UserController;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.Category;
import Proyecto.GestorAlmuerzo.model.Plate;
import Proyecto.GestorAlmuerzo.model.Role;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.service.AppServices;
import Proyecto.GestorAlmuerzo.service.CategoryServices;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
    @InjectMocks
    private UserController userController;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServices categoryServices;
    @Mock
    private PlateRepository plateRepository;

    @InjectMocks
    private PlateServices plateServices;

    @Test
    public void testLoginSuccessful() throws GestorAlmuerzosAppException {
        Role rol = new Role("1", "client");
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

    // Similar tests for other login scenarios (empty password, incorrect information) can be added

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

//    @Test
//    public void ShouldNotLoginWithEmptyPassword()  {
//        try {
//            User user = new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", null, roleRepository);
//            when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
//            boolean result = userService.login("sebassele2008@gmail.com", "");
//            assertFalse(result);
//        } catch (GestorAlmuerzosAppException e){
//            assertEquals(e.getMessage(),GestorAlmuerzosAppException.EmptyPassword);
//        }
//    }

    @Test
    void testGetRol() {
        String roleId = "1";
        Role expectedRole = new Role(roleId, "client");
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));

        Optional<Role> result = appServices.getRol(roleId);

        assertEquals(Optional.of(expectedRole), result);
    }

    @Test
    void testAddRol() {
        Role roleToAdd = new Role("1", "client");

        appServices.addRol(roleToAdd);

        verify(roleRepository, times(1)).save(roleToAdd);
    }

    @Test
    void testUpdateRole() {
        Role roleToUpdate = new Role("1", "updatedRole");
        when(roleRepository.save(Mockito.any(Role.class))).thenReturn(roleToUpdate);

        Role result = appServices.updateRole(roleToUpdate);

        assertEquals(roleToUpdate, result);
    }

    @Test
    void testGetAllRoles() {
        List<Role> expectedRoles = Collections.singletonList(new Role("1", "client"));
        when(roleRepository.findAll()).thenReturn(expectedRoles);

        List<Role> result = appServices.getAllRoles();

        assertEquals(expectedRoles, result);
    }

    @Test
    void testDeleteUser() {
        String roleIdToDelete = "1";

        appServices.deleteUser(roleIdToDelete);

        verify(roleRepository, times(1)).deleteById(roleIdToDelete);
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
        Plate expectedPlate = new Plate(1, "Test Plate", "Description", 10, categories, "picture.jpg");
        when(plateRepository.findAll()).thenReturn(Collections.singletonList(expectedPlate));

        List<Plate> result = plateServices.getAllPlates();

        assertEquals(Collections.singletonList(expectedPlate), result);
    }

    @Test
    void testGetPlateById() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        int plateId = 1;
        Plate expectedPlate = new Plate(plateId, "Test Plate", "Description", 10, categories, "picture.jpg");
        when(plateRepository.findById(plateId)).thenReturn(Optional.of(expectedPlate));

        Optional<Plate> result = plateServices.getPlateById(plateId);

        assertEquals(Optional.of(expectedPlate), result);
    }

    @Test
    void testAddPlate() {
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Plate plateToAdd = new Plate(1, "New Plate", "Description", 15, categories, "new_picture.jpg");

        when(plateRepository.save(plateToAdd)).thenReturn(plateToAdd);

        Plate result = plateServices.addPlate(plateToAdd);

        assertEquals(plateToAdd, result);
    }

    @Test
    void testUpdatePlate() {
        HashSet<Category> categories = new HashSet<>();
        categories.add(new Category("Category1"));
        Plate plateToUpdate = new Plate(1, "Updated Plate", "Updated Description", 20, categories, "updated_picture.jpg");

        plateServices.updatePlate(plateToUpdate);

        verify(plateRepository, times(1)).save(plateToUpdate);
    }

    @Test
    void testDeletePlate() {
        int plateId = 1;
        plateServices.deletePlate(plateId);
        verify(plateRepository, times(1)).deleteById(plateId);
    }
}
