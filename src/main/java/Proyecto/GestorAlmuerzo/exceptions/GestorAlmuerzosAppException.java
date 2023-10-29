package Proyecto.GestorAlmuerzo.exceptions;

public class GestorAlmuerzosAppException extends Exception{
    public static final String EmptyName = "Correo Vacio, Porfavor llenar el campo";
    public static final String EmptyPassword = "Contraceña Vacia, Porfavor llenar el campo";
    public static final String IncorrectInformation = "Información Incorrecta. Porfavor verifique datos";
    public static final String UserExist = "El usuario ya existe. Porfavor cambien información";

    public GestorAlmuerzosAppException(String msm) {
        super(msm);
    }
}
