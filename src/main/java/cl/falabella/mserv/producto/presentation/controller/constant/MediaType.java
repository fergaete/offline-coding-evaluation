package cl.falabella.mserv.producto.presentation.controller.constant;

public class MediaType extends org.springframework.http.MediaType {

    private static final long serialVersionUID = -3091705911488931708L;

    public static final String APPLICATION_HAL_JSON = "application/hal+json";

    public MediaType (String type) {
        super (type);
    }
}
