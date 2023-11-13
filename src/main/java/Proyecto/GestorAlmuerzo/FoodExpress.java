package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.service.AppServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
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
            usuarioService.addUser(new User("Sebastian","sebastian@foodexpress.com","Cliente"));
        };

    }

}
