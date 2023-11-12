package Proyecto.GestorAlmuerzo.controller;


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

    @GetMapping("/Register")
    public String showUserRegisterForm(@PathVariable( value = "id") String id,Model m){
        m.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userRepository.addUser(user);
        return "redirect:/index";
    }

    @GetMapping("/UpdateProfile/{id}")
    public String showUserUpdateForm(@PathVariable( value = "id") String id,Model m){
        Optional<User> usuario = userRepository.getUser(id);
        m.addAttribute("user",usuario);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user){
        userRepository.updateUser(user);
        return "redirect:/index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        userRepository.deleteUser(id);
        return "redirect:/employee";
    }

}
