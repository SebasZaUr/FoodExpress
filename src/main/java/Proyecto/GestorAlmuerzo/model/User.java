package Proyecto.GestorAlmuerzo.model;

import java.lang.reflect.InvocationTargetException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
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
    @Column
    private Role role;

    /**
     * El constructor de la clase User.
     *
     * @param email  El correo del Usuario
     * @param password La contraceña de la cuenta del ususario
     * @param role  Que tipo de usuario es.
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public User(String email, String password, String role) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.email = email;
        this.password = password;
        this.role = (Role) Class.forName(role).getConstructor().newInstance();
    }


    /**
     * Me devuelve el Tipo de usuario que es.
     * @return  El tipo de usuario.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Me devuelve el correo del usuario
     * @return  El correo del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Me devuelve la contraceña del usuario.
     * @return  La contraceña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Me permite cambiar el correo del usuario.
     * @param email El nuevo correo del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Me permite cambiar la contraceña del usuario.
     * @param password  La nueva contraceña.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
