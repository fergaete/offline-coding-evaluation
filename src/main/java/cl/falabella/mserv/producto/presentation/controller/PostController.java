package cl.falabella.mserv.producto.presentation.controller;

import cl.falabella.mserv.producto.application.command.create.CreateCommand;
import cl.falabella.mserv.producto.application.command.create.CreateHandler;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.exception.DomainException;
import cl.falabella.mserv.producto.domain.exception.EntityNotFoundException;
import cl.falabella.mserv.producto.domain.exception.ProductAlreadyExistException;
import cl.falabella.mserv.producto.presentation.controller.constant.ErrorResponse;
import cl.falabella.mserv.producto.presentation.controller.constant.MediaType;
import cl.falabella.mserv.producto.presentation.controller.constant.Routes;
import cl.falabella.mserv.producto.presentation.controller.form.CreateForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = {"Products"}, description = "Microservicio de productos")
@RestController()
@EnableAutoConfiguration
public class PostController extends BaseController {

    private final CreateHandler _handler;

    public PostController(CreateHandler handler) {
        _handler = handler;
    }

    @ApiOperation(value = "Crear producto.", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ProductDTO.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @PostMapping(value = Routes.Productos.POST, produces = MediaType.APPLICATION_HAL_JSON)
    @CrossOrigin(value = "*", methods = RequestMethod.POST)
    public ResponseEntity<?> post(
            @Valid @RequestBody CreateForm form
    ) {
        log.info("Posting productos");
        log.info("Form: {}", form);
        try {
            return this.createOKResponse(
                    _handler.handler(
                        new CreateCommand(
                                form.getName(),
                                form.getBrand(),
                                form.getSize(),
                                form.getSku(),
                                form.getPrice(),
                                form.getImage()
                        )
                    )
            );
        } catch (DomainException | ProductAlreadyExistException e) {
            log.error(e.getMessage(), e);
            return this.createBadRequestResponse(e.getMessage());
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            return this.createNotFoundResponse(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return this.createInternalServerErrorResponse();
        }
    }
}
