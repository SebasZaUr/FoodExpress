package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Plate")
@AllArgsConstructor
@NoArgsConstructor
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

    @Column
    private String idPago;

    @Column
    private String userId;

    @Column
    @OneToMany
    private List<Plate> plates;

    public Order(int id) {
        this.id = id;
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

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getIdUsuario() {
        return userId;
    }

    public void setIdUsuario(String idUsuario) {
        this.userId = idUsuario;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", idPago='" + idPago + '\'' +
                ", idUsuario='" + userId + '\'' +
                ", plates=" + plates +
                '}';
    }
}
