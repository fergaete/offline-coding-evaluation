package cl.falabella.mserv.producto.presentation.controller.constant;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Integer codigo;
    private final String  mensaje;

    public ErrorResponse(Integer codigo, String mensaje) {
        this.codigo  = codigo;
        this.mensaje = mensaje;
    }
}