package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Entidad de la base de datos que guarda todos la información de los tipos de
 * usuaríos
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/10/2023
 */
@Entity(name = "Role")
@Table(name = "ROL")
public class Role {
    @Id
    @Column(name = "ID")
    protected String id;

    @Column(name = "Category")
    protected String category;

    @OneToMany(mappedBy = "role")
    private List<User> usuarios;

    public Role(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public Role() {

    }

    /**
     * Devuelve la descripción del tipo de cliente
     * 
     * @return la descripción del tipo de cliente
     */
    public String getNombre() {
        return category;
    }

    /**
     * Me permite cambiar la descripción del tipo de cliente
     * 
     * @param category La nueva descripción del tipo de cliente
     **/
    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return this.id;
    }
}
