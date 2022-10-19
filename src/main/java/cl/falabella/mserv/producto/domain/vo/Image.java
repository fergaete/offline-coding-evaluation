package cl.falabella.mserv.producto.domain.vo;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode
@ApiModel(description = "Image")
public class Image implements ValueObject {

    private static final String PATTERN = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    @ApiModelProperty(notes = "_value")
    private final @NonNull String _value;

    public Image(@NonNull String value) {
        var valueVar = value.strip();
        if (valueVar.isBlank() || valueVar.isEmpty()) {
            throw new DomainException("La imágen no puede ser un valor vacío.");
        }

        if (!this._validate(valueVar)) {
            throw new DomainException(String.format("La imágen %s posee un formato inválido.", valueVar));
        }

        this._value = valueVar;
    }

    @Override public String toString() {
        return this._value;
    }

    @Override
    public String value() {
        return this.toString();
    }

    private Boolean _validate(@NonNull String value) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}