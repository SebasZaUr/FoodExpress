package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.OrderRepository;
import Proyecto.GestorAlmuerzo.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * servicions que puede utilizar una orden
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/11/2023
 */
@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
    @Transactional
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

}
