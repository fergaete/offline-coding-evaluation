package cl.falabella.mserv.producto.application.command.create;

import cl.falabella.mserv.producto.application.command.CommandHandlerInterface;
import cl.falabella.mserv.producto.application.service.ProductService;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.repository.UniqueSKUSpecificationInterface;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class CreateHandler implements CommandHandlerInterface {

    private final @NonNull ProductService _productService;
    private final @NonNull UniqueSKUSpecificationInterface _uniqueSKUSpecification;

    public CreateHandler(
            @NonNull ProductService productService,
            @NonNull UniqueSKUSpecificationInterface uniqueSKUSpecification
    ) {
        _productService = productService;
        _uniqueSKUSpecification = uniqueSKUSpecification;
    }

    public ProductDTO handler(CreateCommand command) {
        log.info("CreateHandler.handler() called");
        log.info("CreateHandler.handler() command: {}", command);

        Product product = new Product(
                command.name(),
                command.brand(),
                command.size(),
                command.sku(),
                command.price(),
                command.image(),
                _uniqueSKUSpecification
        );

        log.info("CreateHandler.handler() product: {}", product);

        _productService.save(product);

        log.info("CreateHandler.handler() command finished");

        return _productService.findBySKU(command.sku());
    }
}
