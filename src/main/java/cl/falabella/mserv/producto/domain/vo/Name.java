package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;

import java.util.Objects;

public class Name extends BaseStringValueObject {

    public Name(String value) {
        if (value == null || value.isEmpty()) {
            throw new DomainException("El atributo Name no puede ser un valor vacío.");
        }

        if (this._validate(value)) {
            throw new DomainException(String.format("El atributo Name %s posee un formato inválido.", value));
        }

        this._value = value;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass()) return false;
        Name name = (Name) otro;
        return Objects.equals(this._value, name._value);
    }
}