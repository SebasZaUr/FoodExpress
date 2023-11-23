package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.Repository.RoleRepository;
import Proyecto.GestorAlmuerzo.model.Category;
import Proyecto.GestorAlmuerzo.service.AppServices;
import Proyecto.GestorAlmuerzo.service.CategoryServices;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class FoodExpress {
    @Autowired
    RoleRepository roleRepository;
    @Autowired(required = true)
    UserServices usuarioService;

    @Autowired(required = true)
    AppServices appService;

    @Autowired(required = true)
    CategoryServices categoryServices;

    @Autowired(required = true)
    PlateServices plateServices;
    public static void main(String[] args) {
        SpringApplication.run(FoodExpress.class, args);
    }



    @Bean
    public CommandLineRunner run() throws Exception {
        return (args) -> {
            appService.addRol(new Role("1", "client"));
            appService.addRol(new Role("2", "admin"));
            appService.addRol(new Role("3", "cooker"));
            appService.addRol(new Role("4", "waiter"));
            usuarioService.addUser(new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", "client",roleRepository));
            usuarioService.addUser(new User("christian@foodexpres.com", "Chirstian", "Duarte", "1234", "admin",roleRepository));
            usuarioService.addUser(new User("cesar@foodexpres.com", "Cesar", "Amaya", "1234", "cooker",roleRepository));
            usuarioService.addUser(new User("johann@foodexpres.com", "Johann", "Amaya", "1234", "waiter",roleRepository));
            categoryServices.addCategory(new Category("Ensalada"));
            categoryServices.addCategory(new Category("Postre"));
            categoryServices.addCategory(new Category("Entradas"));
            categoryServices.addCategory(new Category("Plato Principal"));
            categoryServices.addCategory(new Category("Especiales"));
            categoryServices.addCategory(new Category("Nuevo Plato"));
            Set<Category> defaulCategoria = new HashSet<>();
            defaulCategoria.add(categoryServices.getCategoryByName("salads"));
            plateServices.addPlate(new Plate(1, "Brillo Andino", "Ensalada de quinoa con aguacate y tomate cherry",12000,defaulCategoria,"../images/plates/quinoa.jpg"));
            plateServices.addPlate(new Plate(2, "Delicia Marina", "Carpaccio de salmón con alcaparras y limón",13000,defaulCategoria,"../images/plates/capaccio.jpg"));
            plateServices.addPlate(new Plate(3, "Susurros Tropicales", "Crema de calabaza con jengibre y coco",11000,defaulCategoria,"../images/plates/crema.jpg"));
            plateServices.addPlate(new Plate(4, "Aromas de la Tierra", "Sopa de lentejas con chorizo y espinacas",12300,defaulCategoria,"../images/plates/sopa.jpg"));
            plateServices.addPlate(new Plate(5, "Melodía de Sabores", "Solomillo de ternera con salsa de vino tinto",11800,defaulCategoria,"../images/plates/solomillo.jpg"));
            plateServices.addPlate(new Plate(6, "Fusión Exótica", "Pollo al curry con leche de coco y verduras",14000,defaulCategoria,"../images/plates/pollo.jpg"));
            plateServices.addPlate(new Plate(7, "Ritmo del Mar", "Filete de salmón a la parrilla con salsa de eneldo",15000,defaulCategoria,"../images/plates/filete.jpg"));
            plateServices.addPlate(new Plate(8, "Viaje a la Costa", "Paella de mariscos",13200,defaulCategoria,"../images/plates/paella.jpg"));
            plateServices.addPlate(new Plate(9, "Armonía en el Plato", "Risotto de champiñones y espinacas",11500,defaulCategoria,"../images/plates/risotto.jpg"));
            plateServices.addPlate(new Plate(10, "Capricho Vegetal", "Lasaña de berenjena con ricotta y espinacas",14500,defaulCategoria,"../images/plates/lasagnaB.jpg"));
            plateServices.addPlate(new Plate(11, "Dulce Contraste", "Puré de batata con nueces y miel",10000,defaulCategoria,"../images/plates/pure.jpg"));
            plateServices.addPlate(new Plate(12, "Elegancia Verde", "Brócoli al vapor con almendras tostadas",10800,defaulCategoria,"../images/plates/brocoli.jpg"));
            plateServices.addPlate(new Plate(13, "Tradición Italiana", "Lasaña boloñesa",12100,defaulCategoria,"../images/plates/lasagna.jpg"));
            plateServices.addPlate(new Plate(14, "Fiesta Mexicana", "Tacos de carnitas con salsa de aguacate",11500,defaulCategoria,"../images/plates/tacos.jpg"));
        };
    }}
