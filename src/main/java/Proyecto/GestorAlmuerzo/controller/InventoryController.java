package proyecto.gestorAlmuerzo.controller;
import proyecto.gestorAlmuerzo.model.Ingredient;
import proyecto.gestorAlmuerzo.service.IngredientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/inventory")
public class InventoryController {

    @Autowired
    private IngredientServices ingredientServices;

    @GetMapping
    public String showMenu(Model model) {

        List<Ingredient> ingredients;
        ingredients = ingredientServices.getAllIngredients();

        model.addAttribute("ingredients", ingredients);
        return "admin/inventory";
    }

    @PostMapping("/addIngredient")
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientServices.addIngredient(ingredient);
        return "redirect:admin/inventory";
    }

    @GetMapping("/editIngredient/{id}")
    public String showEditPlateForm(@PathVariable String id, Model model) {
        int ingredientId = Integer.parseInt(id);
        Optional<Ingredient> ingredient = ingredientServices.getIngredientById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "admin/editIngredient";
    }

    @PostMapping("/editIngredient/{id}")
    public String editPlate(@PathVariable String id, @ModelAttribute("ingredient") Ingredient ingredient) {
        int plateId = Integer.parseInt(id);
        Optional<Ingredient> existingPlate = ingredientServices.getIngredientById(plateId);
        if(existingPlate.isEmpty()) {
        }
        else {
            ingredient.setId((long) plateId);
            ingredientServices.updatePlate(ingredient);
        }
        return "redirect:/admin/inventory";
    }

    @PostMapping("/deleteIngredient/{id}")
    public String deletePlate(@PathVariable String id) {
        int plateId = Integer.parseInt(id);
        ingredientServices.deletePlate(plateId);
        return "redirect:/admin/inventory";
    }
}

