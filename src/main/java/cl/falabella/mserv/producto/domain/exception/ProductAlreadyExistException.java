package cl.falabella.mserv.producto.domain.exception;

public class ProductAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -179647026731747896L;

    public ProductAlreadyExistException(String message) {
        super (message);
    }

    public ProductAlreadyExistException(String message, Throwable cause) {
        super (message, cause);
    }
}