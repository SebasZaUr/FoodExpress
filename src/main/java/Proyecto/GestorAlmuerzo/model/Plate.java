package Proyecto.GestorAlmuerzo.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Setter
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate plate)) return false;
        return getId() == plate.getId() && getPrice() == plate.getPrice() && Objects.equals(getName(), plate.getName()) && Objects.equals(getDescription(), plate.getDescription()) && Objects.equals(getPicture(), plate.getPicture()) && Objects.equals(ruta, plate.ruta) && Objects.equals(getCategories(), plate.getCategories()) && Objects.equals(ingredients, plate.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getPicture(), ruta, getCategories(), ingredients);
    }

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private String picture;

    @Lob
    @Column(name = "ruta", length = 1048576)
    private String ruta;

    @ManyToMany
    @JoinTable(
            name = "plate_categories",
            joinColumns = @JoinColumn(name = "plate_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "plate_ingredient",
            joinColumns = @JoinColumn(name = "plate_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    /**
     * El constructor de la clase Plate.
     *
     * @param name    El nombre del plato
     */
    public Plate(Integer id, String name, String description, int price, Set<Category> categories, Set<Ingredient> ingredients, String picture){
        this.id= id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
        this.ingredients = ingredients;
        this.picture = picture;
    }
    public Plate(){

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", categories=" + categories +
                ", ingredients=" + ingredients +
                '}';
    }
}
