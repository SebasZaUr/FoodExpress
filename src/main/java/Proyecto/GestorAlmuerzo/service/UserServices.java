package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.UserRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.User;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository UserRepository;

    @Value("${spring.mail.password}")
    private String password;

    public boolean login(String email, String password) throws GestorAlmuerzosAppException {
        if (email.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmptyEmail);
        }
        if (password.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EmptyPassword);
        }
        Optional<User> newUser = getUser(email);
        User usuario = newUser.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.IncorrectInformation));
        String encryptPassword = usuario.encrypt(password);
        String userPassword = usuario.getPassword();
        return encryptPassword.equals(userPassword);
    }

    public Optional<User> getUser(String email) {
        return UserRepository.findById(email);
    }

    public User addUser(User user) {
        return UserRepository.save(user);
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

    public void sendEmailForgotPassword(User user) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("password" + password);
                return new PasswordAuthentication("foodexpressadm", password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("foodexpressadm@yandex.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Recuperar Contraseña");

            String resetPasswordLink = "https://tu-sitio.com/reset-password?token=abcd1234";
            String emailContent = String.format(
                    "Hola %s %s,\n\nDale click al siguiente link para que recuperes tu contraseña:\n%s",
                    user.getNombre(), user.getApellido(), resetPasswordLink);

            message.setText(emailContent);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

