package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserServices userRepository;

    @GetMapping("/login")
    public String showUserLogin(Model m) {
        return "login";
    }

    @PostMapping("/verifyPorfiel")
    public String verifyLogin(@RequestAttribute("correo") String correo,
            @RequestAttribute("contraseña") String contraseña)
            throws GestorAlmuerzosAppException {
        String dirijir = "login";
        if (userRepository.login(correo, contraseña)) {
            dirijir = "redirect:/index";
        }
        return dirijir;
    }

    @GetMapping("/register")
    public String showUserRegisterForm(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.addUser(user);
        return "redirect:/index";
    }

    @GetMapping("/UpdateProfile/{id}")
    public String showUserUpdateForm(@PathVariable(value = "id") String id, Model m) {
        Optional<User> usuario = userRepository.getUser(id);
        m.addAttribute("user", usuario);
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

}
