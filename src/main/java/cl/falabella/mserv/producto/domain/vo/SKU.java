package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SKU implements ValueObject {

    private static final String PATTERN = "FAL-\\b([0-9]{7,9})";

    private final String _value;

    public SKU(String value) {
        if (value == null || value.isEmpty()) {
            throw new DomainException("El SKU no puede ser un valor vacío.");
        }

        log.info("SKU: " + value.toString());
        log.info("Validate: " + _validate(value));
        if (!this._validate(value)) {
            log.error(String.format("El SKU %s posee un formato inválido.", value));
            throw new DomainException(String.format("El SKU %s posee un formato inválido.", value));
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
        SKU sku = (SKU) otro;
        return Objects.equals(this._value, sku._value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._value);
    }

    private Boolean _validate(String value) {
        Pattern pattern = Pattern.compile(SKU.PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}