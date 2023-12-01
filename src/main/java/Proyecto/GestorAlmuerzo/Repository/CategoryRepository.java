package proyecto.gestorAlmuerzo.repository;

import proyecto.gestorAlmuerzo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String categoryName);
}
