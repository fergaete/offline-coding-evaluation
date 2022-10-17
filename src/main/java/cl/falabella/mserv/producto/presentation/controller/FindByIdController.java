package cl.falabella.mserv.producto.presentation.controller;

import cl.falabella.mserv.producto.domain.exception.DomainException;
import cl.falabella.mserv.producto.domain.exception.EntityNotFoundException;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.model.ProductId;
import cl.falabella.mserv.producto.domain.repository.ProductRepositoryInterface;
import cl.falabella.mserv.producto.presentation.controller.constant.ErrorResponse;
import cl.falabella.mserv.producto.presentation.controller.constant.MediaType;
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
@RestController
@EnableAutoConfiguration
public class FindByIdController extends BaseController {

    private final ProductRepositoryInterface _productRepository;

    public FindByIdController(ProductRepositoryInterface productRepository) {
        _productRepository = productRepository;
    }

    @ApiOperation(value = "Obtener producto por ID", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Product.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @GetMapping(value = Routes.Productos.GET_PRODUCT_BY_ID, produces = MediaType.APPLICATION_HAL_JSON)
    @CrossOrigin(value = "*", methods = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable String id) {
        log.info(String.format("Obteniendo información del producto id: [%s]", id));
        try {
            return this.createOKResponse(_productRepository.findById(new ProductId(id)));
        } catch (DomainException de) {
            log.info(de.getMessage());
            return this.createBadRequestResponse(de.getMessage());
        } catch (EntityNotFoundException enfe) {
            log.info(enfe.getMessage());
            return this.createNotFoundResponse(enfe.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return this.createInternalServerErrorResponse();
        }
    }
}