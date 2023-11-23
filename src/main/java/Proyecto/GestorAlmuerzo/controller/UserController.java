package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.Repository.UserRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.Plate;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.SocketOption;
import java.util.Optional;
import java.util.List;
@Controller
public class UserController {
    private String userLogin;

    @Autowired
    UserServices userRepository;
    @Autowired
    PlateServices plateServices;
    @Autowired
    UserRepository repository;

    @GetMapping("/login")
    public String showUserLogin(Model model) {
        return "login";
    }

    @PostMapping("/verifyProfile")
    public String verifyLogin(@RequestParam("correo") String correo,
                              @RequestParam("contraseña") String contraseña, Model model)
            throws GestorAlmuerzosAppException {
        String redirect = "login";
        try {
            if (userRepository.login(correo, contraseña)) {
                Optional<User> u = userRepository.getUser(correo);
                User usuario = u.orElseThrow();
                userLogin = usuario.getNombre().split(" ")[0] + " " + usuario.getApellido().split(" ")[0];
                redirect = "redirect:/user/" + usuario.getRole();
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
        String username = (String)m.getAttribute("username");
        m.addAttribute("username",userLogin);
        return "user/client";
    }

    @GetMapping("/admin")
    public String loginAdmin(Model m){
        String username = (String)m.getAttribute("username");
        m.addAttribute("username",userLogin);
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
        userRepository.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateProfile/{id}")
    public String showUserUpdateForm(@PathVariable(value = "id") String id, Model model) {
        Optional<User> usuario = userRepository.getUser(id);
        model.addAttribute("user", usuario.orElse(new User())); // Handle the case where the user is not found
        return "user/update";
    }

     @GetMapping("/")
    public String index(Model model){
        List<Plate> menu = plateServices.getAllPlates();
        model.addAttribute("menu", menu);
        return "index";
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
        Optional<User> usuario = userRepository.getUser(email);
        try {
            User user = usuario.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmailNoExist));
            userRepository.sendEmailForgotPassword(user);
        }catch(GestorAlmuerzosAppException e){
            model.addAttribute("error", e.getMessage());
            return("/forgotPassword");
        }
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userRepository.updateUser(user);
        return "redirect:/user/client";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userRepository.deleteUser(id);
        return "redirect:/user/client";
    }
}

