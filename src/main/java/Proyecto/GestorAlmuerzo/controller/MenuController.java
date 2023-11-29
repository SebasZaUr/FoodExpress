package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.model.Category;
import Proyecto.GestorAlmuerzo.model.Plate;
import Proyecto.GestorAlmuerzo.service.CategoryServices;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private PlateServices plateServices;

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping
    public String showMenu(@RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
                           @RequestParam(name = "categoryFilter",required = false) Long categoryId,
                           Model model) {

        List<Plate> menu;

        if(categoryId != null){
            menu =  plateServices.findByCategoriesId(categoryId);
        }else {

            if ("name".equals(sortBy)) {
                menu = plateServices.getAllPlatesOrderedByName();
            } else if ("price".equals(sortBy)) {
                menu = plateServices.getAllPlatesOrderedByPrice();
            } else {
                menu = plateServices.getAllPlates();
            }
        }
        List<Category> allCategories = categoryServices.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("menu", menu);
        return "admin/menu";
    }


    @PostMapping("/addPlate")
    public String addPlate(@ModelAttribute("plate") Plate plate) {
        plateServices.addPlate(plate);
        return "redirect:/admin/menu";
    }

    @GetMapping("/editPlate/{id}")
    public String showEditPlateForm(@PathVariable String id, Model model) {
        int plateId = Integer.parseInt(id);
        Optional<Plate> plate = plateServices.getPlateById(plateId);
        List<Category> allCategories = categoryServices.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("plate", plate);
        return "admin/editPlate";
    }

    @PostMapping("/editPlate/{id}")
    public String editPlate(@PathVariable String id, @ModelAttribute("plate") Plate plate) {
        int plateId = Integer.parseInt(id);
        Optional<Plate> existingPlate = plateServices.getPlateById(plateId);
        if(existingPlate.isEmpty()) {
        }
        else {
            plate.setId(plateId);
            plateServices.updatePlate(plate);
        }
        return "redirect:/admin/menu";
    }

    @RequestMapping("/deletePlate/{id}")
    public String deletePlate(@PathVariable String id) {
        int plateId = Integer.parseInt(id);
        plateServices.deletePlate(plateId);
        return "redirect:/admin/menu";
    }

}

