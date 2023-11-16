package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "Plate")
/**
 * Entidad de la base de datos que guarda todos la información de los platos
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/10/2023
 */
public class Plate {
    @Id
    private Long id;
    @Column
    private String name;

    @Override
    public String toString() {
        return "Plate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Column
    private String description;

    /**
     * El constructor de la clase Plate.
     *
     * @param name    El nombre del plato
     */
    public Plate(String id, String name, String description){
        this.id= Long.parseLong(id);
        this.name = name;
        this.description = description;
    }
    public Plate(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
