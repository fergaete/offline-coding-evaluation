package cl.falabella.mserv.producto.domain.exception;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 6757935883952286321L;

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}