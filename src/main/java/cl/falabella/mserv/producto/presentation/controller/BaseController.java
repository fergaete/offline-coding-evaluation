package cl.falabella.mserv.producto.presentation.controller;

import cl.falabella.mserv.producto.presentation.controller.constant.EmptyResponse;
import cl.falabella.mserv.producto.presentation.controller.constant.ErrorResponse;
import cl.falabella.mserv.producto.presentation.controller.constant.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseController {

    public ResponseEntity<ErrorResponse> createCreatedResponse(String mensaje) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorResponse(ResponseStatusCode.CREATED_CODE.getCode(), mensaje));
    }

    public ResponseEntity<ErrorResponse> createOKResponse() {
        return ResponseEntity.ok(new ErrorResponse(ResponseStatusCode.OK_CODE.getCode(), Messages.OK));
    }

    public ResponseEntity<?> createOKResponse(Object data) {
        return ResponseEntity.ok(data);
    }

    public ResponseEntity<EmptyResponse> createEmptyResponse() {
        return ResponseEntity.ok(new EmptyResponse(ResponseStatusCode.ACCEPTED_CODE.getCode(), Messages.OK));
    }

    public ResponseEntity<ErrorResponse> createNotFoundResponse(String mensaje) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ResponseStatusCode.NOT_FOUND_CODE.getCode(), mensaje));
    }

    public ResponseEntity<ErrorResponse> createForbiddenResponse() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(ResponseStatusCode.FORBIDDEN_CODE.getCode(), Messages.FORBIDDEN_ERROR));
    }

    public ResponseEntity<ErrorResponse> createBadRequestResponse(String mensaje) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ResponseStatusCode.BAD_REQUEST_CODE.getCode(), mensaje));
    }

    public ResponseEntity<ErrorResponse> createInternalServerErrorResponse() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ResponseStatusCode.INTERNAL_SERVER_CODE.getCode(), Messages.INTERNAL_SERVER_ERROR));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, Object> errores  = new HashMap<>();
        response.put("codigo", ResponseStatusCode.BAD_PARAMETERS_CODE.getCode());
        response.put("mensaje", "Su solicitud contiene errores");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName    = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });
        response.put("errores", errores);
        return response;
    }

    private enum ResponseStatusCode {
        ACCEPTED_CODE(2),
        CREATED_CODE(1),
        OK_CODE(0),
        NOT_FOUND_CODE(-1),
        FORBIDDEN_CODE(-2),
        BAD_PARAMETERS_CODE(-3),
        BAD_REQUEST_CODE(-4),
        INTERNAL_SERVER_CODE(-5);

        private final int code;

        ResponseStatusCode(int code) {
            this.code = code;
        }

        private int getCode() {
            return code;
        }
    }
}