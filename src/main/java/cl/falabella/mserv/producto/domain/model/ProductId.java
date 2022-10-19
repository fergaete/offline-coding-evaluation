package cl.falabella.mserv.producto.domain.model;

import cl.falabella.mserv.producto.domain.vo.UUIDv4;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
public final class ProductId extends UUIDv4 {

    public ProductId(@NonNull String value) {
        super(value);
    }
}