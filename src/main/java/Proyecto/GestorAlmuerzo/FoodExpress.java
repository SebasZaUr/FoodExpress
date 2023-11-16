package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.service.AppServices;
import Proyecto.GestorAlmuerzo.service.UserServices;
import Proyecto.GestorAlmuerzo.service.PlateServices;
import Proyecto.GestorAlmuerzo.model.User;
import Proyecto.GestorAlmuerzo.model.Plate;
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

    @Autowired
    PlateServices plateServices;
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
            plateServices.addPlate(new Plate("1","Brillo Andino","Ensalada de quinoa con aguacate y tomate cherry"));
            plateServices.addPlate(new Plate("2","Delicia Marina","Carpaccio de salmón con alcaparras y limón"));
            plateServices.addPlate(new Plate("3","Susurros Tropicales","Crema de calabaza con jengibre y coco"));
            plateServices.addPlate(new Plate("4","Aromas de la Tierra","Sopa de lentejas con chorizo y espinacas"));
            plateServices.addPlate(new Plate("5","Melodía de Sabores","Solomillo de ternera con salsa de vino tinto"));
            plateServices.addPlate(new Plate("6","Fusión Exótica","Pollo al curry con leche de coco y verduras"));
            plateServices.addPlate(new Plate("7","Ritmo del Mar","Filete de salmón a la parrilla con salsa de eneldo"));
            plateServices.addPlate(new Plate("8","Viaje a la Costa","Paella de mariscos"));
            plateServices.addPlate(new Plate("9","Armonía en el Plato","Risotto de champiñones y espinacas"));
            plateServices.addPlate(new Plate("10","Capricho Vegetal","Lasaña de berenjena con ricotta y espinacas"));
            plateServices.addPlate(new Plate("11","Dulce Contraste","Puré de batata con nueces y miel"));
            plateServices.addPlate(new Plate("12","Elegancia Verde","Brócoli al vapor con almendras tostadas"));
            plateServices.addPlate(new Plate("13","Tradición Italiana","Lasaña boloñesa"));
            plateServices.addPlate(new Plate("14","Fiesta Mexicana","Tacos de carnitas con salsa de aguacate"));


            plateServices.getAllPlates().forEach(plate -> System.out.println(plate));
        };


    }

}
