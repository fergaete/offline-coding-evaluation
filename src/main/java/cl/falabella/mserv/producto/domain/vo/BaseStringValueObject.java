package cl.falabella.mserv.producto.domain.vo;

import java.util.Objects;

public class BaseStringValueObject implements ValueObject {

    public final static int MIN_LENGTH = 3;
    public final static int MAX_LENGTH = 50;
    protected String _value;


    @Override
    public String toString() {
        return this._value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this._value);
    }

    protected boolean _validate(String value) {
        int length = value.length();
        return length < MIN_LENGTH || length > MAX_LENGTH;
    }
}