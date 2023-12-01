package proyecto.gestorAlmuerzo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.gestorAlmuerzo.model.Role;

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
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByCategory(String category);

    boolean existsByCategory(String category);
}
