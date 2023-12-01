package proyecto.gestorAlmuerzo.service;

import proyecto.gestorAlmuerzo.repository.IngredientRepository;
import proyecto.gestorAlmuerzo.model.Ingredient;
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
    public void updatePlate(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deletePlate(int plateId) {
        ingredientRepository.deleteById(plateId);
    }
}
