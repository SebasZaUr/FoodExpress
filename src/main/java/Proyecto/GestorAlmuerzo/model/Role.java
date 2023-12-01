package proyecto.gestorAlmuerzo.model;

import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "Category")
    protected String category;

    public Role( String category) {
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
        return category.toLowerCase();
    }

    /**
     * Me permite cambiar la descripción del tipo de cliente
     * 
     * @param category La nueva descripción del tipo de cliente
     **/
    public void setCategory(String category) {
        this.category = category.toUpperCase();
    }

    public int getId() {
        return this.id;
    }
}
