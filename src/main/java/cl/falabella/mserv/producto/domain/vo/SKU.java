package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@EqualsAndHashCode
@ApiModel(description = "SKU")
public class SKU implements ValueObject {

    private static final String PATTERN = "FAL-\\b([0-9]{7,9})";

    @ApiModelProperty(notes = "_value")
    private final String _value;

    public SKU(String value) {
        if (value.isBlank() || value.isEmpty()) {
            throw new DomainException("El SKU no puede ser un valor vacío.");
        }

        log.info("SKU ingresado: " + value);
        log.info("Resultado validación: " + _validate(value));
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
    public String value() {
        return this.toString();
    }

    private Boolean _validate(@NonNull String value) {
        Pattern pattern = Pattern.compile(SKU.PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}