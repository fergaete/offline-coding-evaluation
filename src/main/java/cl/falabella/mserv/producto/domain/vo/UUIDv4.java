package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@ToString
public class UUIDv4 {

    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9A-Za-z]{8}-[0-9A-Za-z]{4}-4[0-9A-Za-z]{3}-[89ABab][0-9A-Za-z]{3}-[0-9A-Za-z]{12}");
    private final String _value;

    public UUIDv4(String value) {
        if (value == null || value.isEmpty()) {
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
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass()) return false;
        UUIDv4 uuid = (UUIDv4) otro;
        return Objects.equals(this._value, uuid._value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._value);
    }
}
