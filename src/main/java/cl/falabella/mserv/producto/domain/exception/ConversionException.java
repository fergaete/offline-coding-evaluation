package cl.falabella.mserv.producto.domain.exception;

public class ConversionException extends RuntimeException {

    private static final long serialVersionUID = 8414199725019475198L;

    public ConversionException(String message) {
        super (message);
    }

    public ConversionException(String message, Throwable cause) {
        super (message, cause);
    }
}
