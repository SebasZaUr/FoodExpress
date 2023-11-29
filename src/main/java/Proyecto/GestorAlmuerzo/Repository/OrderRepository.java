package Proyecto.GestorAlmuerzo.Repository;

import Proyecto.GestorAlmuerzo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int id);
}
