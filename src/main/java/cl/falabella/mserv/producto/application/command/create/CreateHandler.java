package cl.falabella.mserv.producto.application.command.create;

import cl.falabella.mserv.producto.application.command.CommandHandlerInterface;
import cl.falabella.mserv.producto.application.service.ProductService;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.repository.UniqueSKUSpecificationInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class CreateHandler implements CommandHandlerInterface {

    private final ProductService _productService;
    private final UniqueSKUSpecificationInterface _uniqueSKUSpecification;

    public CreateHandler(ProductService productService, UniqueSKUSpecificationInterface uniqueSKUSpecification) {
        _productService = productService;
        _uniqueSKUSpecification = uniqueSKUSpecification;
    }

    public ProductDTO handler(CreateCommand command) {
        _productService.save(
                new Product(
                        command.name(),
                        command.brand(),
                        command.size(),
                        command.sku(),
                        command.price(),
                        _uniqueSKUSpecification
                )
        );

        return _productService.findBySKU(command.sku());
    }
}
