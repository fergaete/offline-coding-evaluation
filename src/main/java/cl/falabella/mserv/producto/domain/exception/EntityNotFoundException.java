package cl.falabella.mserv.producto.domain.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4536425667253151307L;

    public EntityNotFoundException(String message) {
        super (message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}
