package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de la base de datos que guarda todos la información de los tipos de usuaríos
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/10/2023
 */
@Entity
@Table(name = "ROL")
public abstract class Role {
    @Id
    @Column
    protected String id;

    @Column
    protected String Nombre;


    /**
     * Devuelve la descripción del tipo de cliente
     * @return  la descripción del tipo de cliente
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Me permite cambiar la descripción del tipo de cliente
     * @param Nombre La nueva descripción del tipo de cliente
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
