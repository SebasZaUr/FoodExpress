package Proyecto.GestorAlmuerzo.Repository;

import Proyecto.GestorAlmuerzo.model.Role;
import Proyecto.GestorAlmuerzo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface que nos permite conectarnos con la base de datos
 * @author Sebastian Zamora Urrego
 * @version 28/10/2023
 */
@Repository
public interface AppRepository extends JpaRepository<User, String> {
    public List<Role> findById();

}
