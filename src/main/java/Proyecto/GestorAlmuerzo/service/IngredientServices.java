package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.IngredientRepository;
import Proyecto.GestorAlmuerzo.model.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.gestorAlmuerzo.exceptions.GestorAlmuerzosAppException;

import java.util.List;
import java.util.Optional;

import static proyecto.gestorAlmuerzo.exceptions.GestorAlmuerzosAppException.IngredientInUse;

@Service
public class IngredientServices {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Optional<Ingredient> getIngredientById(long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) throws GestorAlmuerzosAppException {
        Ingredient ingredient = ingredientRepository.getReferenceById(ingredientId);
        if(ingredient.getPlates().isEmpty()){
            ingredientRepository.deleteById(ingredientId);
        } else {
            throw new GestorAlmuerzosAppException(IngredientInUse);
        }
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    public List<Ingredient> getAllIngredientsByIds(List<Long> idsIngredients) {
        return ingredientRepository.findAllById(idsIngredients);
    }
}
