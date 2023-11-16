package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.service.AppServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.model.Cliente;
import Proyecto.GestorAlmuerzo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FoodExpress {
    @Autowired
    UserServices usuarioService;

    @Autowired
    AppServices appService;

    public static void main(String[] args) {
        SpringApplication.run(FoodExpress.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return (args) -> {
            appService.addRol(new Role("1", "cliente"));
            appService.addRol(new Role("2", "administrador"));
            appService.addRol(new Role("3", "empleado"));
            usuarioService.addUser(new User("sebastian@foodexpres.com", "Sebastian", "1234", "1"));
        };

    }

}
