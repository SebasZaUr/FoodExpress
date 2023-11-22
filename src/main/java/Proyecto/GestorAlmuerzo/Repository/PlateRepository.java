package Proyecto.GestorAlmuerzo.Repository;

import Proyecto.GestorAlmuerzo.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface que nos permite conectar con la base de datos.
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 14/11/2023
 */
@Repository
public interface PlateRepository extends JpaRepository<Plate, Integer> {

    List<Plate> findByCategoriesId(Long categoryId);
}
