package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @ManyToMany
    @JoinTable(
            name = "plate_categories",
            joinColumns = @JoinColumn(name = "plate_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    /**
     * El constructor de la clase Plate.
     *
     * @param name    El nombre del plato
     */
    public Plate(Integer id, String name, String description, int price,Set<Category> categories){
        this.id= id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int plateId) {
        this.id = plateId;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories){
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
