package proyecto.gestorAlmuerzo.service;

import proyecto.gestorAlmuerzo.repository.RoleRepository;
import proyecto.gestorAlmuerzo.repository.UserRepository;
import proyecto.gestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import proyecto.gestorAlmuerzo.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean login(String email, String password) throws GestorAlmuerzosAppException {
        if (email.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmptyEmail);
        }
        if (password.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmptyPassword);
        }
        Optional<User> newUser = getUser(email);
        User usuario = newUser.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmailNoExist));
        String encryptPassword = usuario.encrypt(password);
        String userPassword = usuario.getPassword();
        return encryptPassword.equals(userPassword);
    }

    public Optional<User> getUser(String email) {
        return UserRepository.findById(email);
    }

    public void addUser(User user,boolean role) {
        UserRepository.save(user);
        if(role){
            user.setRole("client",roleRepository);
            UserRepository.save(user);
        }
    }

    public void updateUser(User user) {
        Optional<User> usuario = UserRepository.findById(user.getEmail());
        User u = usuario.orElseThrow();
        String role = u.getRole();
        user.setRole(role,roleRepository);
        UserRepository.save(user);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public void deleteUser(String id) {
        UserRepository.deleteById(id);
    }

}

