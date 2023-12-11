package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.Repository.UserRepository;
import Proyecto.GestorAlmuerzo.model.*;
import Proyecto.GestorAlmuerzo.service.IngredientServices;
import Proyecto.GestorAlmuerzo.service.OrderServices;
import proyecto.gestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;

@Controller
public class UserController {
    private Random num = new Random();
    private int valorTotal;
    private String userLogin;
    @Autowired
    PlateServices plateServices;
    @Autowired
    UserServices userServices;
    @Autowired
    IngredientServices ingredientServices;
    @Autowired
    UserRepository repository;

    @Autowired
    OrderServices orderServices;

    private Order order;
    private List<Plate> plateList = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model){
        List<Plate> menu = plateServices.getAllPlates();
        setValues(model);
        model.addAttribute("menu", menu);
        return "index";
    }

    @GetMapping("/login")
    public String showUserLogin(Model model) {
        return "login";
    }

    @PostMapping("/verifyProfile")
    public String verifyLogin(@RequestParam("correo") String correo,
                              @RequestParam("contraseña") String password, Model model)
            throws GestorAlmuerzosAppException {
        String redirect = "login";
        try {
            if (userServices.login(correo, password)) {
                Optional<User> u = userServices.getUser(correo);
                User usuario = u.orElseThrow();
                userLogin = correo;
                redirect = "redirect:/" + usuario.getRole();
            }else{
                model.addAttribute("error", GestorAlmuerzosAppException.IncorrectInformation);
                return redirect;
            }
        }catch (GestorAlmuerzosAppException e){
            model.addAttribute("error", e.getMessage());
            return redirect;
        }
        return redirect;
    }

    @GetMapping("/client")
    public String loginUser(Model m){
        setValues(m);
        Optional<User> user = userServices.getUser(userLogin);
        ArrayList<Ingredient> preferences = new ArrayList<>(user.get().getPreferences());
        ArrayList<Ingredient> bannedIngredients = new ArrayList<>(userServices.getUser(userLogin).get().getBannedIngredients());
        List<Plate> menu = plateServices.getAllPlates();
        List<Plate> filteredPlates = plateServices.getFilteredPlates(menu, preferences, bannedIngredients);
        m.addAttribute("menu", filteredPlates);
        return "user/client";
    }

    @GetMapping("/admin")
    public String loginAdmin(Model m){
        setValues(m);
        return "user/admin";
    }
    @GetMapping("/register")
    public String showUserRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("nombre") String name,
                           @RequestParam("apellido") String lastName,@RequestParam("email") String email,@RequestParam("password") String password,
                           @RequestParam("confirm_password") String confirm,@ModelAttribute("user") User user,Model model) {
        if(repository.findById(email).isPresent()){
            model.addAttribute("error", GestorAlmuerzosAppException.EmailExist);
            return "register";
        }
        if(!password.equals(confirm)){
            model.addAttribute("error", GestorAlmuerzosAppException.NotPasswordConcident);
            return "register";
        }
        if(name.isEmpty()){
            model.addAttribute("error", GestorAlmuerzosAppException.NameEmpty);
            return "register";
        }
        if(lastName.isEmpty()){
            model.addAttribute("error", GestorAlmuerzosAppException.LastNameEmpty);
            return "register";
        }
        if(email.isEmpty()){
            model.addAttribute("error", GestorAlmuerzosAppException.EmptyEmail);
            return "register";
        }
        UserDTO usuario = new UserDTO(name,lastName,email);
        userServices.addUser(user,true);
        String retu = user.getRole();
        setValues(model);
        return "redirect:/" + retu;
    }

    @GetMapping("/updateProfile/{id}")
    public String showUserUpdateForm(@PathVariable(value = "id") String id, Model model) {
        setValues(model);
        Optional<User> usuario = userServices.getUser(id);
        model.addAttribute("user", usuario.orElse(new User())); // Handle the case where the user is not found
        return "user/update";
    }


    @GetMapping("/forgotPassword")
    public String forgotPassword(){
        return "user/forgotPassword";
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        return "about-us";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        return "contact-us";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email,Model model) throws GestorAlmuerzosAppException {
        Optional<User> usuario = userServices.getUser(email);
        try {
            User user = usuario.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmailNoExist));
        }catch(GestorAlmuerzosAppException e){
            model.addAttribute("error", e.getMessage());
            return("/user/forgotPassword");
        }
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userServices.updateUser(user);
        return "redirect:/client";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userLogin=null;
        userServices.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/userInfo")
    public String viewUserInfo(Model m){
        setValues(m);
        Optional<User> usuario =repository.findById(userLogin);
        User user = usuario.orElseThrow();
        m.addAttribute("user",user);
        return "user/user-info";
    }

    @PostMapping("/logout")
    public String logout(){
        userLogin = null;
        return "redirect:/";
    }

    private void setValues(Model m){
        if(userLogin != null){
            Optional<User> usuario =repository.findById(userLogin);
            User user = usuario.orElseThrow();
            String fullName = user.getNombre().split(" ")[0] + " "+user.getApellido().split(" ")[0];
            m.addAttribute("username",fullName);
            m.addAttribute("link","yes");
            m.addAttribute("numberPlate",plateList.size());
        }
    }

    @GetMapping("/usersRoles")
    private String showAllUsers(Model m){
        List<User> listUsers = userServices.getAllUsers();
        m.addAttribute("Usuarios", listUsers);
        return "admin/listUsers";
    }

    @GetMapping("/preferences")
    public String showPreferences(Model model) {
        List<Ingredient> allIngredients = ingredientServices.getAllIngredients();
        Optional<User> user = userServices.getUser(userLogin);
        if (user.isPresent()) {
            Set<Ingredient> preferences = user.get().getPreferences();
            Set<Ingredient> bannedIngredients = user.get().getBannedIngredients();
            model.addAttribute("bannedIngredients",bannedIngredients);
            model.addAttribute("preferences", preferences);
            model.addAttribute("allIngredients",allIngredients);
        }
        setValues(model);
        return "preferences";
    }

    @PostMapping("/savePreferences")
    public String savePreferences(@RequestParam(value = "ingredientIds", required = false) List<Integer> ingredientIds, Model model) {
        Optional<User> user = userServices.getUser(userLogin);
        if (user.isPresent()) {
            User existingUser = user.get();

            // Eliminar ingredientes de disgustos si ya están en las preferencias
            if (existingUser.getBannedIngredients() != null && ingredientIds != null) {
                for (int ingredientId : ingredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(ingredientId);
                    ingredientOptional.ifPresent(existingUser.getBannedIngredients()::remove);
                }
            }

            Set<Ingredient> existingPreferences = existingUser.getPreferences();
            if (existingPreferences == null) {
                existingPreferences = new HashSet<>();
            }

            if (ingredientIds != null) {
                for (int ingredientId : ingredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(ingredientId);
                    ingredientOptional.ifPresent(existingPreferences::add);
                }
            }
            existingUser.setPreferences(existingPreferences);
            userServices.updateUser(existingUser);
        }

        return "redirect:/preferences";
    }

    @PostMapping("/saveBannedIngredients")
    public String saveBannedIngredients(@RequestParam(value = "bannedIngredientIds", required = false) List<Integer> bannedIngredientIds,Model model){
        Optional<User> user = userServices.getUser(userLogin);
        if (user.isPresent()) {
            User existingUser = user.get();

            // Eliminar ingredientes de preferencias si ya están en los disgustos
            if (existingUser.getPreferences() != null && bannedIngredientIds != null) {
                for (int bannedIngredientId : bannedIngredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(bannedIngredientId);
                    ingredientOptional.ifPresent(existingUser.getPreferences()::remove);
                }
            }

            Set<Ingredient> existingBanned = existingUser.getBannedIngredients();
            if (existingBanned == null) {
                existingBanned = new HashSet<>();
            }
            if (bannedIngredientIds != null) {
                for (int bannedIngredientId : bannedIngredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(bannedIngredientId);
                    ingredientOptional.ifPresent(existingBanned::add);
                }
            }
            existingUser.setBannedIngredients(existingBanned);
            userServices.updateUser(existingUser);
        }

        return "redirect:/preferences";
    }


    @PostMapping("/deletePreferences")
    public String deletePreferences(@RequestParam(value = "ingredientIds", required = false) List<Integer> ingredientIds, Model model) {
        Optional<User> user = userServices.getUser(userLogin);
        if (user.isPresent()) {
            User existingUser = user.get();

            Set<Ingredient> existingPreferences = existingUser.getPreferences();
            if (existingPreferences != null && ingredientIds != null) {
                for (int ingredientId : ingredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(ingredientId);
                    ingredientOptional.ifPresent(existingPreferences::remove);
                }
                existingUser.setPreferences(existingPreferences);
                userServices.updateUser(existingUser);
            }
        }

        return "redirect:/preferences";
    }

    @PostMapping("/deleteBannedIngredients")
    public String deleteBannedIngredients(@RequestParam(value = "bannedIngredientIds", required = false) List<Integer> bannedIngredientIds,
             Model model) {
        Optional<User> user = userServices.getUser(userLogin);
        if (user.isPresent()) {
            User existingUser = user.get();

            Set<Ingredient> existingBannedIngredients = existingUser.getBannedIngredients();
            if (existingBannedIngredients != null && bannedIngredientIds != null) {
                for (int bannedIngredientId : bannedIngredientIds) {
                    Optional<Ingredient> ingredientOptional = ingredientServices.getIngredientById(bannedIngredientId);
                    ingredientOptional.ifPresent(existingBannedIngredients::remove);
                }
                existingUser.setBannedIngredients(existingBannedIngredients);
                userServices.updateUser(existingUser);
            }
        }

        return "redirect:/preferences";
    }

    @PostMapping("/addToCart")
    private String addPlateToCart(@RequestParam("id") int plateId,Model m){
        Optional<Plate> plate = plateServices.getPlateById(plateId);
        Plate plato =plate.orElseThrow();
        plateList.add(plato);
        m.addAttribute("countOrder",plateList.size());
        setValues(m);
        return "redirect:/client";
    }

    @GetMapping("cart")
    private String listPlatesOrder(Model m){
        setValues(m);
        valorTotal = 0;
        for (Plate plato:plateList){
            valorTotal += plato.getPrice();
        }
        m.addAttribute("precio",valorTotal);
        m.addAttribute("platos",plateList);
        return "user/buy's-cart";
    }

    @PostMapping("payOrder")
    private String payOrder(Model m){
        Optional<User> usuario = userServices.getUser(userLogin);
        User user = usuario.orElseThrow();
        List<Order> orderList = user.getOrdenes();
        Date fecahHoy= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order = new Order(orderList.size()+1,dateFormat.format(fecahHoy),user,valorTotal);
        for(Plate plato: plateList){
            order.addOrderPlate(plato);
        }
        plateList = new ArrayList<>();
        orderList.add(order);
        orderServices.addOrder(order);
        return "redirect:/facture";
    }

    @GetMapping("/facture")
    private String generateFacture(Model m){
        m.addAttribute("order",order);
        long randomLong = Math.abs(num.nextLong());
        m.addAttribute("numFactura",randomLong);
        setValues(m);
        return "user/factura";
    }

}

