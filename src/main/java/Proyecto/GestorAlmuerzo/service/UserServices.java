package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.AppRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private AppRepository UserRepository;
    /**
     * Verifica que el ususario si este registrado, si esta le da acceso a ala plataforma
     * @param email Correo del Usuario.
     * @param password Contraceña del Usuario.
     * @throws GestorAlmuerzosAppException  Empy
     */
    public void login(String email, String password) throws GestorAlmuerzosAppException {
        if(email.equals("")) throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.Emptyemail);
        if(password.equals("")) throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmptyPassword);
        Optional<User> newUser = getUser(email);
    }

    /**
     *
     * @param email      user's email
     * @param password  user's password
     * @param role      user's role
     * @throws NoSuchMethodException     Couldn't found the method.
     * @throws ClassNotFoundException    Couldn't found a class with this email.
     * @throws InstantiationException    Couldn't create an instance of this class.
     * @throws IllegalAccessException    Couldn't access the class ´cuz don't have permissions.
     * @throws InvocationTargetException Wraps an Exception.
     */
    public void singup(String email, String password, String role) throws GestorAlmuerzosAppException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(getUser(email)==null){
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.UserExist);
        }else{
            new User(email,password,role);
        }
    }

    /**
     * Me Obtiene un usuario atravez de su id.
     * @param email  Correo del usuario.
     * @return  El usuario si se encontro.
     */
    public Optional<User> getUser(String email){
        return UserRepository.findById(email);
    }

    /**
     * Me agrega un usuario a la base de datos
     * @param user  El ususarío que voy agregar
     * @return El usuarío que se agrego a la base de datos
     */
    public User addUser(User user){
        return UserRepository.save(user);
    }

    /**
     * Me actualiza la información de un usuario.
     * @param user  El usuario que voy actualizar.
     * @return  El usuarío actualizado en la base de datos.
     */
    public User updateUser(User user){
        UserRepository.findById(user.getEmail());
        return UserRepository.save(user);
    }

    /**
     * Me obtiene todos los usuaríos en la base de datos.
     * @return  La lista de los usuariós de la base de datos.
     */
    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    /**
     * Me elimina un usuarío.
     * @param id El id del usuarío que voy a eliminar.
     */
    public void deleteUser(String id){
        UserRepository.deleteById(id);
    }
}
