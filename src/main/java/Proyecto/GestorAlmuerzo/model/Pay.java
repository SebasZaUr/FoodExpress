package Proyecto.GestorAlmuerzo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pago")
public class Pay {
    @Id
    @Column
    private String  idPay;

    @Column
    private int price;

    @Column
    private String Method;

    @OneToOne
    private User usuario;

}
