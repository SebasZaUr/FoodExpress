package proyecto.gestorAlmuerzo.repository;

import proyecto.gestorAlmuerzo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface que nos permite conectar con la base de datos.
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/11/2023
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findUserById(int id);
}
