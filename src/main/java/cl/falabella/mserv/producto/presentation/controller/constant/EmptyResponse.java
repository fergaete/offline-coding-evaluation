package cl.falabella.mserv.producto.presentation.controller.constant;

import lombok.Getter;

@Getter
public class EmptyResponse {
    private final Integer codigo;
    private final String  mensaje;

    public EmptyResponse(Integer codigo, String mensaje) {
        this.codigo  = codigo;
        this.mensaje = mensaje;
    }
}