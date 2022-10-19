package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class UUIDv4 implements ValueObject {

    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9A-Za-z]{8}-[0-9A-Za-z]{4}-4[0-9A-Za-z]{3}-[89ABab][0-9A-Za-z]{3}-[0-9A-Za-z]{12}");
    private final @NonNull String _value;

    public UUIDv4(@NonNull String value) {
        if (value.isBlank() || value.isEmpty()) {
            throw new DomainException("El UIID no deber ser nulo o vacío.");
        }

        if (!UUID_PATTERN.matcher(value).matches()) {
            throw new DomainException(String.format("El valor ingresado (%s) no es un UUID válido.", value));
        }

        this._value = value;
    }

    @Override
    public String toString() {
        return this._value;
    }

    @Override
    public String value() {
        return this.toString();
    }
}
