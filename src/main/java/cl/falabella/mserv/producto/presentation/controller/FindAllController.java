package cl.falabella.mserv.producto.presentation.controller;

import cl.falabella.mserv.producto.domain.DTO.PaginatedRepresentation;
import cl.falabella.mserv.producto.domain.repository.ProductRepositoryInterface;
import cl.falabella.mserv.producto.presentation.controller.constant.ErrorResponse;
import cl.falabella.mserv.producto.presentation.controller.constant.MediaType;
import cl.falabella.mserv.producto.presentation.controller.constant.PaginationSwagger;
import cl.falabella.mserv.producto.presentation.controller.constant.Routes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"Products"}, description = "Microservicio de productos")
@RestController()
@EnableAutoConfiguration
public class FindAllController extends BaseController {

    private final ProductRepositoryInterface _productRepositoy;

    public FindAllController(ProductRepositoryInterface productRepositoy) {
        _productRepositoy = productRepositoy;
    }

    @ApiOperation(value = "Obtener listado paginado de productos", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PaginatedRepresentation.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @GetMapping(value = Routes.Productos.GET_ALL_PRODUCTS, produces = MediaType.APPLICATION_HAL_JSON)
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(required = false, defaultValue = PaginationSwagger.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = PaginationSwagger.DEFAULT_SIZE) Integer size
    ) {
        log.info("Obteniendo productos");
        try {
            return this.createOKResponse(_productRepositoy.findAll(page, size));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return this.createInternalServerErrorResponse();
        }
    }
}