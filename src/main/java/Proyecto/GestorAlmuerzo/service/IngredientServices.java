package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.IngredientRepository;
import Proyecto.GestorAlmuerzo.model.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServices {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> getIngredientById(int ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(int plateId) {
        ingredientRepository.deleteById(plateId);
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }
}
