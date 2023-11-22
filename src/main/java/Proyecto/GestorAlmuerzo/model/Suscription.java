package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Suscripcion")
public class Suscription {
    @Id
    @Column(name = "id")
    private String idSuscription;

    @Column(name = "Tipo")
    private String Type;

    @Column(name= "precio")
    private int price;

    @OneToMany(mappedBy = "suscripcion")
    List<User> usersSuscribe;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Suscription(String idSuscription, String type, int price) {
        this.idSuscription = idSuscription;
        Type = type;
        this.price= price;
    }

    public Suscription() {
    }

    public void setIdSuscription(String idSuscription) {
        this.idSuscription = idSuscription;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getIdSuscription() {
        return idSuscription;
    }

    public String getType() {
        return Type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Suscription that)) return false;
        return Objects.equals(getIdSuscription(), that.getIdSuscription()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdSuscription(), getType());
    }

    @Override
    public String toString() {
        return "Suscription{" +
                "idSuscription='" + idSuscription + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
