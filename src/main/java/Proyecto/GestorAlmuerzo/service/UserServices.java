package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.RoleRepository;
import Proyecto.GestorAlmuerzo.Repository.UserRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.User;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import Proyecto.GestorAlmuerzo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public User updateUser(User user) {
        UserRepository.findById(user.getEmail());
        return UserRepository.save(user);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public void deleteUser(String id) {
        UserRepository.deleteById(id);
    }

}

