package Proyecto.GestorAlmuerzo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import Proyecto.GestorAlmuerzo.Repository.RoleRepository;

@Entity
@Table(name = "Usuarios")
/**
 * Entidad de la base de datos que guarda todos la información de los usuaríos
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 28/10/2023
 */
public class User {
    @Id
    @Column
    private String email;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name = "rol", nullable = true)
    private Role role;
    @Column
    private String nombre;
    @Transient
    private String OriginPassword;

    /**
     * El constructor de la clase User.
     *
     * @param email    El correo del Usuario
     * @param password La contraceña de la cuenta del ususario
     * @param role     Que tipo de usuario es.
     */

    public User(String email, String nombre, String password, String role) {
        this.email = email;
        this.password = password;
        this.OriginPassword = password;
        this.nombre = nombre;
    }

    public User() {

    }

    /**
     * Me devuelve el Tipo de usuario que es.
     * 
     * @return El tipo de usuario.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Me devuelve el correo del usuario
     * 
     * @return El correo del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Me devuelve la contraceña del usuario.
     * 
     * @return La contraceña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Me permite cambiar el correo del usuario.
     * 
     * @param email El nuevo correo del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Me permite cambiar la contraceña del usuario.
     * 
     * @param password La nueva contraceña.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
