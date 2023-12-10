package Proyecto.GestorAlmuerzo.controller;

import Proyecto.GestorAlmuerzo.model.Category;
import Proyecto.GestorAlmuerzo.model.Ingredient;
import Proyecto.GestorAlmuerzo.model.Plate;
import Proyecto.GestorAlmuerzo.service.CategoryServices;
import Proyecto.GestorAlmuerzo.service.IngredientServices;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private PlateServices plateServices;

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private IngredientServices ingredientServices;

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
        List<Ingredient> ingredients = ingredientServices.getAllIngredients();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("menu", menu);
        model.addAttribute("ingredients", ingredients);
        return "admin/menu";
    }


    @PostMapping("/addPlate")
    public String addPlate(@ModelAttribute("plate") Plate plate,
                           @RequestParam(name = "ingredients", required = false) List<Long> idsIngredients,
                           @RequestParam(name = "pictureFile") MultipartFile pictureFile) throws IOException {

        byte[] pictureBytes = pictureFile.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(pictureBytes);

        plate.setRuta(base64Image);
        List<Ingredient> ingredients = ingredientServices.getAllIngredientsByIds(idsIngredients);
        Set<Ingredient> ingredientSet = new HashSet<>(ingredients);

        plate.setIngredients(ingredientSet);
        Set<Plate> plates;
        for (Ingredient ingredient: ingredients) {
            plates = ingredient.getPlates();
            plates.add(plate);
            ingredient.setPlates(plates);
        }

        plateServices.addPlate(plate);


        return "redirect:/admin/menu";
    }


    @GetMapping("/editPlate/{id}")
    public String showEditPlateForm(@PathVariable String id, Model model) {
        long plateId = Long.parseLong(id);
        Optional<Plate> plate = plateServices.getPlateById(plateId);
        List<Category> allCategories = categoryServices.getAllCategories();
        List<Ingredient> ingredients = ingredientServices.getAllIngredients();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("plate", plate);
        model.addAttribute("ingredients",ingredients);
        return "admin/editPlate";
    }

    @PostMapping("/editPlate/{id}")
    public String editPlate(@PathVariable String id, @ModelAttribute("plate") Plate plate,
                            @RequestParam(name = "ingredients", required = false) List<Long> idsIngredients) {
        long plateId = Long.parseLong(id);
        Optional<Plate> existingPlate = plateServices.getPlateById(plateId);
        if(existingPlate.isPresent()) {
            List<Ingredient> ingredients = ingredientServices.getAllIngredientsByIds(idsIngredients);
            Set<Ingredient> ingredientSet = new HashSet<>(ingredients);
            plate.setId((int) plateId);
            plate.setIngredients(ingredientSet);
            Set<Plate> plates;
            for (Ingredient ingredient: ingredients) {
                plates = ingredient.getPlates();
                plates.add(plate);
                ingredient.setPlates(plates);
            }
            plateServices.updatePlate(plate);
        }
        return "redirect:/admin/menu";
    }

    @RequestMapping("/deletePlate/{id}")
    public String deletePlate(@PathVariable String id) {
        long plateId = Long.parseLong(id);
        plateServices.deletePlate(plateId);
        return "redirect:/admin/menu";
    }
}


