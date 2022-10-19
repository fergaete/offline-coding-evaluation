package cl.falabella.mserv.producto.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
@ApiModel(description = "Size")
public class Size implements ValueObject {

    @ApiModelProperty(notes = "_value")
    private final @NonNull String _value;

    public Size(@NonNull String value) {
        this._value = value;
    }

    @Override
    public String toString() {  return this._value; }

    @Override
    public String value() {
        return this.toString();
    }
}