package Proyecto.GestorAlmuerzo;

import Proyecto.GestorAlmuerzo.Repository.RoleRepository;
import Proyecto.GestorAlmuerzo.model.*;
import Proyecto.GestorAlmuerzo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import Proyecto.GestorAlmuerzo.model.Role;

import java.util.HashSet;
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

    @Autowired(required = false)
    IngredientServices ingredientServices;

    @Autowired(required = true)
    PlateServices plateServices;
    public static void main(String[] args) {
        SpringApplication.run(FoodExpress.class, args);
    }



    @Bean
    public CommandLineRunner run() throws Exception {
        return (args) -> {
            appService.addRol(new Role( "ROLE_client"));
            appService.addRol(new Role("ROLE_admin"));
            appService.addRol(new Role("ROLE_cooker"));
            appService.addRol(new Role("ROLE_waiter"));
            usuarioService.addUser(new User("sebassele2008@gmail.com", "Sebastian", "Zamora", "1234", "client",roleRepository),false);
            usuarioService.addUser(new User(
                    "christian@foodexpres.com", "Christian", "Duarte", "1234", "admin",roleRepository),false);
            usuarioService.addUser(new User("cesar@foodexpres.com", "Cesar", "Amaya", "1234", "cooker",roleRepository),false);
            usuarioService.addUser(new User("johann@foodexpres.com", "Johann", "Amaya", "1234", "waiter",roleRepository),false);
            categoryServices.addCategory(new Category("Ensalada"));
            categoryServices.addCategory(new Category("Postre"));
            categoryServices.addCategory(new Category("Entradas"));
            categoryServices.addCategory(new Category("Plato Principal"));
            categoryServices.addCategory(new Category("Especiales"));
            categoryServices.addCategory(new Category("Nuevo Plato"));
            Set<Category> defaultCategoria = new HashSet<>();
            defaultCategoria.add(categoryServices.getCategoryByName("Plato Principal"));
            ingredientServices.addIngredient(new Ingredient( "Aceite de oliva", "Extra virgen", 250));
            ingredientServices.addIngredient(new Ingredient( "Sal", "Marina", 500));
            ingredientServices.addIngredient(new Ingredient( "Pimienta", "Negra molida", 100));
            ingredientServices.addIngredient(new Ingredient( "Ajo", "Fresco", 50));
            ingredientServices.addIngredient(new Ingredient( "Cebolla", "Blanca", 200));
            ingredientServices.addIngredient(new Ingredient( "Pasta", "Spaghetti", 300));
            ingredientServices.addIngredient(new Ingredient( "Queso", "Parmesano rallado", 150));
            ingredientServices.addIngredient(new Ingredient( "Tomate", "Cherry", 120));
            ingredientServices.addIngredient(new Ingredient( "Albahaca", "Fresca", 30));
            ingredientServices.addIngredient(new Ingredient( "Calabacín", "Verde", 180));
            ingredientServices.addIngredient(new Ingredient( "Jamón", "Serrano", 120));
            ingredientServices.addIngredient(new Ingredient("Huevo", "Orgánico", 60));
            ingredientServices.addIngredient(new Ingredient( "Pimiento", "Rojo", 140));

            Set<Ingredient> categoriaHuevo = new HashSet<>();
            categoriaHuevo.add(ingredientServices.getIngredientByName("Huevo"));

            Set<Ingredient> categoriaAceiteDeOliva = new HashSet<>();
            categoriaAceiteDeOliva.add(ingredientServices.getIngredientByName("Aceite de oliva"));

            Set<Ingredient> categoriaSal = new HashSet<>();
            categoriaSal.add(ingredientServices.getIngredientByName("Sal"));

            Set<Ingredient> categoriaPimienta = new HashSet<>();
            categoriaPimienta.add(ingredientServices.getIngredientByName("Pimienta"));

            Set<Ingredient> categoriaAjo = new HashSet<>();
            categoriaAjo.add(ingredientServices.getIngredientByName("Ajo"));

            Set<Ingredient> categoriaCebolla = new HashSet<>();
            categoriaCebolla.add(ingredientServices.getIngredientByName("Cebolla"));

            Set<Ingredient> categoriaPasta = new HashSet<>();
            categoriaPasta.add(ingredientServices.getIngredientByName("Pasta"));

            Set<Ingredient> categoriaQueso = new HashSet<>();
            categoriaQueso.add(ingredientServices.getIngredientByName("Queso"));

            Set<Ingredient> categoriaTomate = new HashSet<>();
            categoriaTomate.add(ingredientServices.getIngredientByName("Tomate"));

            Set<Ingredient> categoriaAlbahaca = new HashSet<>();
            categoriaAlbahaca.add(ingredientServices.getIngredientByName("Albahaca"));

            Set<Ingredient> categoriaCalabacin = new HashSet<>();
            categoriaCalabacin.add(ingredientServices.getIngredientByName("Calabacín"));

            Set<Ingredient> categoriaJamon = new HashSet<>();
            categoriaJamon.add(ingredientServices.getIngredientByName("Jamón"));

            Set<Ingredient> categoriaPimiento = new HashSet<>();
            categoriaPimiento.add(ingredientServices.getIngredientByName("Pimiento"));

            plateServices.addPlate(new Plate(1, "Brillo Andino", "Ensalada de quinoa con aguacate y tomate cherry", 12000, defaultCategoria, categoriaHuevo,"../images/plates/quinoa.jpg"));
            plateServices.addPlate(new Plate(2, "Delicia Marina", "Carpaccio de salmón con alcaparras y limón", 13000, defaultCategoria, categoriaPimiento,"../images/plates/capaccio.jpg"));
            plateServices.addPlate(new Plate(3, "Susurros Tropicales", "Crema de calabaza con jengibre y coco", 11000, defaultCategoria, categoriaAceiteDeOliva,"../images/plates/crema.jpg"));
            plateServices.addPlate(new Plate(4, "Aromas de la Tierra", "Sopa de lentejas con chorizo y espinacas", 12300, defaultCategoria, categoriaCebolla,"../images/plates/sopa.jpg"));
            plateServices.addPlate(new Plate(5, "Melodía de Sabores", "Solomillo de ternera con salsa de vino tinto", 11800, defaultCategoria, categoriaTomate,"../images/plates/solomillo.jpg"));
            plateServices.addPlate(new Plate(6, "Fusión Exótica", "Pollo al curry con leche de coco y verduras", 14000, defaultCategoria, categoriaAjo,"../images/plates/pollo.jpg"));
            plateServices.addPlate(new Plate(7, "Ritmo del Mar", "Filete de salmón a la parrilla con salsa de eneldo", 15000, defaultCategoria, categoriaCalabacin,"../images/plates/filete.jpg"));
            plateServices.addPlate(new Plate(8, "Viaje a la Costa", "Paella de mariscos", 13200, defaultCategoria, categoriaJamon,"../images/plates/paella.jpg"));
            plateServices.addPlate(new Plate(9, "Armonía en el Plato", "Risotto de champiñones y espinacas", 11500, defaultCategoria, categoriaSal,"../images/plates/risotto.jpg"));
            plateServices.addPlate(new Plate(10, "Capricho Vegetal", "Lasaña de berenjena con ricotta y espinacas", 14500, defaultCategoria, categoriaQueso,"../images/plates/lasagnaB.jpg"));
            plateServices.addPlate(new Plate(11, "Dulce Contraste", "Puré de batata con nueces y miel", 10000, defaultCategoria, categoriaAlbahaca,"../images/plates/pure.jpg"));
            plateServices.addPlate(new Plate(12, "Elegancia Verde", "Brócoli al vapor con almendras tostadas", 10800, defaultCategoria, categoriaHuevo,"../images/plates/brocoli.jpg"));
            plateServices.addPlate(new Plate(13, "Tradición Italiana", "Lasaña boloñesa", 12100, defaultCategoria, categoriaPimienta,"../images/plates/lasagna.jpg"));
            plateServices.addPlate(new Plate(14, "Fiesta Mexicana", "Tacos de carnitas con salsa de aguacate", 11500, defaultCategoria, categoriaAceiteDeOliva,"../images/plates/tacos.jpg"));

        };
    }}
