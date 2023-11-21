package Proyecto.GestorAlmuerzo.model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Usuario")
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
    @Column
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "suscripcion", nullable = true)
    private Suscription suscripcion;
    //@Autowired
    //private RoleRepository roleRepository;


    /**
     * El constructor de la clase User.
     *
     * @param email    El correo del Usuario
     * @param password La contraceña de la cuenta del ususario
     * @param role     Que tipo de usuario es.
     */

    public User(String email, String name,String lastName, String password, String role) throws GestorAlmuerzosAppException {
        this.email = email;
        this.nombre = name;
        this.apellido=lastName;
        this.password=encrypt(password);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public User() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
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
        this.password= encrypt(password);
    }

    /**
     * Me permite encriptar la contraseña para guardarla en la base de datos
     *
     * @param password La contraseña que voy a encriptar.
     */
    public String encrypt(String password){
        String encryptPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hexStringBuilder.append(String.format("%02X", b));
            }
            encryptPassword = hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptPassword;
    }

    public boolean verifyPassword(String userPassword){
        String prueba = encrypt(userPassword);
        return prueba.equals(this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getRole(), user.getRole()) && Objects.equals(getNombre(), user.getNombre()) && Objects.equals(getApellido(), user.getApellido()) && Objects.equals(suscripcion, user.suscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getRole(), getNombre(), getApellido(), suscripcion);
    }
}
