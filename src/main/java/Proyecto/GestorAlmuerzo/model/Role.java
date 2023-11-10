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
@Table(name = "Tipos de Usuarios")
public abstract class Role {
    @Id
    @Column
    private String id;

    @Column
    private String type;

    /**
     * Constructor de la clase Role
     * @param id    El id del tipo de cliente
     * @param type  La descripción del tipo de cliente
     */
    public Role(String id, String type){
        this.id =id;
        this.type = type;
    }

    public Role() {

    }

    /**
     * Devuelve la descripción del tipo de cliente
     * @return  la descripción del tipo de cliente
     */
    public String getType() {
        return type;
    }

    /**
     * Me permite cambiar la descripción del tipo de cliente
     * @param type La nueva descripción del tipo de cliente
     */
    public void setType(String type) {
        this.type = type;
    }
}
