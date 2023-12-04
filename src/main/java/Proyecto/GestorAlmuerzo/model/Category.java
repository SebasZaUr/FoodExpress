package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Plate> plates = new HashSet<>();

    // constructores, getters y setters

    public Category() {
        // Constructor vacío necesario para JPA
    }

    public Category(String name) {
        this.name = name;
    }

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Plate> getPlates() {
        return plates;
    }

    public void setPlates(Set<Plate> plates) {
        this.plates = plates;
    }
}
