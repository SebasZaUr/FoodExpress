package proyecto.gestorAlmuerzo.repository;

import proyecto.gestorAlmuerzo.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}

