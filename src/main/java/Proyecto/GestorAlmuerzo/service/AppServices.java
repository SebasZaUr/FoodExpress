package proyecto.gestorAlmuerzo.service;

import proyecto.gestorAlmuerzo.repository.RoleRepository;
import proyecto.gestorAlmuerzo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppServices {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Me devuelve el rol
     * 
     * @param id El nombre del rol
     * @return El rol seleccionado.
     */
    public Optional<Role> getRol(int id) {
        return roleRepository.findById(id);
    };

    /**
     * Me añade un nuevo rol
     *
     * @param rol El nuevo rol que voy agregar.
     */
    public void addRol(Role rol) {
        roleRepository.save(rol);
    }

    /**
     * Me actualiza la información de un usuario.
     * 
     * @param rol El usuario que voy actualizar.
     * @return El usuarío actualizado en la base de datos.
     */
    public Role updateRole(Role rol) {
        return roleRepository.save(rol);
    }

    /**
     * Me obtiene todos los usuaríos en la base de datos.
     * 
     * @return La lista de los usuariós de la base de datos.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Me elimina un usuarío.
     * 
     * @param id El id del usuarío que voy a eliminar.
     */
    public void deleteUser(int id) {
        roleRepository.deleteById(id);
    }
}
