package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.text.DecimalFormat;

@EqualsAndHashCode
@ApiModel(description = "Price")
public class Price implements ValueObject {

    private static final String FORMAT_DECIMAL = "#.##";
    private static final double MIN_VALUE = 1.00;
    private static final double MAX_VALUE = 99999999.00;

    @ApiModelProperty(notes = "_value")
    private final @NonNull double _value;

    public Price(@NonNull double value) {
        if (value < MIN_VALUE ) {
            throw new DomainException(String.format("El precio no puede ser menor a %s", String.valueOf(MIN_VALUE)));
        }

        if (value > MAX_VALUE ) {
            throw new DomainException(String.format("El precio no puede ser mayor a %s", String.valueOf(MAX_VALUE)));
        }

        this._value = value;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(Price.FORMAT_DECIMAL);
        return String.format("%s", decimalFormat.format(_value));
    }

    public double toDouble() {
        return this._value;
    }

    @Override
    public Double value() {
        return this.toDouble();
    }
}