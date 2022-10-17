package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import java.util.Objects;

public class Brand extends BaseStringValueObject {
    
    public Brand(String value) {
        if (value == null || value.isEmpty()) {
            throw new DomainException("El atributo Brand no puede ser un valor vacío.");
        }

        if (this._validate(value)) {
            throw new DomainException(String.format("El atributo Brand %s posee un formato inválido.", value));
        }

        this._value = value;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass()) return false;
        Brand brand = (Brand) otro;
        return Objects.equals(this._value, brand._value);
    }
}