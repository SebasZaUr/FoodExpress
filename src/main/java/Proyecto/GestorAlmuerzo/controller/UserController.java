package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.Plate;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserServices userRepository;
    @Autowired
    PlateServices plateServices;

    @GetMapping("/login")
    public String showUserLogin(Model model) {
        return "login";
    }

    @PostMapping("/verifyProfile")
    public String verifyLogin(@RequestParam("correo") String correo,
                              @RequestParam("contraseña") String contraseña, Model model)
            throws GestorAlmuerzosAppException {
        String redirect = "login";
        if (userRepository.login(correo, contraseña)) {
            redirect = "redirect:/";
        }
        return redirect;
    }

    @GetMapping("/register")
    public String showUserRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        userRepository.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateProfile/{id}")
    public String showUserUpdateForm(@PathVariable(value = "id") String id, Model model) {
        Optional<User> usuario = userRepository.getUser(id);
        model.addAttribute("user", usuario.orElse(new User())); // Handle the case where the user is not found
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userRepository.updateUser(user);
        return "redirect:/index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userRepository.deleteUser(id);
        return "redirect:/index";
    }
    @GetMapping("/Menu")
    public String showMenu(Model m) {
        List<Plate> menu= plateServices.getAllPlates();
        m.addAttribute("menu", menu);
        return "menu";
    }
}

