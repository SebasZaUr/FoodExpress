package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Suscripcion")
public class Suscription {
    @Id
    @Column(name = "id")
    private String idSuscription;

    @Column(name = "Tipo")
    private String Type;

    public Suscription(String idSuscription, String type) {
        this.idSuscription = idSuscription;
        Type = type;
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
