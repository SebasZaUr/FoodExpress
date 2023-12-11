package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Orders")
/**
 * Entidad de la base de datos que guarda la informacion de un pedido
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/11/2023
 */
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "orden", nullable = true)
    private User idUsuario;

    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Plate> orderPlates;

    @Column
    private boolean pay = false;

    @Column
    private int price;



    public Order(int id, String fecha, User idUsuario,int price) {
        this.id = id;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.price = price;
//        this.plates = plates;
    }

    public Order() {
    }

    public Order(int orderId) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }



    public String getIdUsuario() {
        return idUsuario.getEmail();
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int countPlate(){
        return orderPlates.size();
    }

    public List<Plate> getOrderPlates() {
        return orderPlates;
    }

    public void setOrderPlates(List<Plate> plates) {
        this.orderPlates = plates;
    }

    public void pay() {
        this.pay = true;
    }

    public void addOrderPlate(Plate plate){
        if (orderPlates == null) {
            List<Plate> plates = new ArrayList<>();
            plates.add(plate);
            orderPlates = plates;
        } else {
            orderPlates.add(plate);
        }
    }

    public int countPlates(){
        int value =0;
        if(orderPlates != null){
            value =orderPlates.size();
        }
        return value;
    }

    public void deletePlate(Plate plato){
        orderPlates.remove(plato);
    }

    public void setPay() {
        this.pay = false;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPay() {
        return pay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", idUsuario='" + idUsuario.getEmail() + '\'' +
//                ", plates=" + plates +
                '}';
    }
}