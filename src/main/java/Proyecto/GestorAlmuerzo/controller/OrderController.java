package Proyecto.GestorAlmuerzo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/order")
    public String showOrder(Model model) {
        return "order";
    }
}
