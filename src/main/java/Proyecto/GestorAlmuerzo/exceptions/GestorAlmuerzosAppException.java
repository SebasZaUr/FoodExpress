package proyecto.gestorAlmuerzo.exceptions;

public class GestorAlmuerzosAppException extends Exception{
    public static final String EmptyPassword = "Contraseña Vacia, Porfavor llenar el campo";
    public static final String IncorrectInformation = "Información Incorrecta. Porfavor verifique datos";
    public static final String EmailExist = "El correo ya esta registrado, por favor escoja otro";
    public static String EmptyEmail = "El usuario debe rellenar el campo de email";
    public static String NotPasswordConcident = "Ambas contraseñas no coinciden , por favor revisar";

    public static String NameEmpty = "Falta Nombre";
    public static String LastNameEmpty = "Falta Apellido";

    public  static String IngredientInUse = "El ingrediente esta en uso, porfavor borrelo de los platos e" +
            " intentelo otra vez";

    public static final String EmailNoExist = "El correo no esta registrado, por favor ingrese un correo valido";

    public static final String RoleNotExist = "El rol asignado no existe";
    public GestorAlmuerzosAppException(String msm) {
        super(msm);
    }
}
