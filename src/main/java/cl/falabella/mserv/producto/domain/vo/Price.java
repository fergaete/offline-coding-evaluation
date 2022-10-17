package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;

import java.text.DecimalFormat;
import java.util.Objects;

public class Price implements ValueObject {

    private static final String FORMAT_DECIMAL = "#.##";
    private static final double MIN_VALUE = 1.00;
    private static final double MAX_VALUE = 99999999.00;
    private static double _value = 0;

    public Price(double value) {
        if (value < MIN_VALUE ) {
            throw new DomainException(String.format("El precio no puede ser menor a %s", String.valueOf(MIN_VALUE)));
        }

        if (value > MAX_VALUE ) {
            throw new DomainException(String.format("El precio no puede ser mayor a %s", String.valueOf(MAX_VALUE)));
        }

        _value = value;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(Price.FORMAT_DECIMAL);
        return String.format("%s", decimalFormat.format(_value));
    }

    public double toDouble() {
        return value();
    }

    public double value() {
        return _value;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null || getClass() != otro.getClass()) return false;
        Price precio = (Price) otro;
        return Objects.equals(_value, precio.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(_value);
    }
}
