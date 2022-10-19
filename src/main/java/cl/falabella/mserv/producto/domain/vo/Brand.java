package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
@ApiModel(description = "Brand")
public final class Brand implements ValueObject {

    public final static int MIN_LENGTH = 3;
    public final static int MAX_LENGTH = 50;

    @ApiModelProperty(notes = "_value")
    private final String _value;

    public Brand(String value) {
        if (value.isBlank() || value.isEmpty()) {
            throw new DomainException("El atributo Brand no puede ser un valor vacío.");
        }

        if (this._validate(value)) {
            throw new DomainException(String.format("El atributo Brand (%s) posee un formato inválido.", value));
        }

        this._value = value;
    }

    private boolean _validate(@NonNull String value) {
        int length = value.length();
        return length < MIN_LENGTH || length > MAX_LENGTH;
    }

    @Override
    public String toString() {
        return _value;
    }

    @Override
    public String value() {
        return this.toString();
    }
}