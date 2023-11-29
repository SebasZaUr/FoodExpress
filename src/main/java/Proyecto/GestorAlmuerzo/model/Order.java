package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Plate")
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
    private String idUsuario;

    @Column
    private List<Plate> plates;

    public Order(int id, String fecha, String idPago, String idUsuario, List<Plate> plates) {
        this.id = id;
        this.fecha = fecha;
        this.idPago = idPago;
        this.idUsuario = idUsuario;
        this.plates = plates;
    }

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
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
                ", idUsuario='" + idUsuario + '\'' +
                ", plates=" + plates +
                '}';
    }
}
