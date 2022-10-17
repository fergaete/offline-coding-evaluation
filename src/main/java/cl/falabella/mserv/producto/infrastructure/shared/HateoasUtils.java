package cl.falabella.mserv.producto.infrastructure.shared;

public class HateoasUtils {

    public static String removePort(String input){
        return input.replaceFirst(":\\d+", "");
    }
}
