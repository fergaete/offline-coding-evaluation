package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Image implements ValueObject {
    private static final String PATTERN = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
    private final String _value;

    public Image(String value) {
        if (value == null || value.isEmpty()) {
            throw new DomainException("La imágen no puede ser un valor vacío.");
        }

        if (!this._validate(value)) {
            throw new DomainException(String.format("La imágen %s posee un formato inválido.", value));
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
        Image image = (Image) otro;
        return Objects.equals(this._value, image._value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._value);
    }

    private Boolean _validate(String value) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
