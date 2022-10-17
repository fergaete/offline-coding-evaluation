package cl.falabella.mserv.producto.domain.vo;

import java.util.Objects;

public class Size implements ValueObject {

    private final String _value;

    public Size(String value) {
        this._value = value;
    }


    @Override
    public String toString() {
        return this._value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._value);
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass()) return false;
        Size size = (Size) otro;
        return Objects.equals(this._value, size._value);
    }
}