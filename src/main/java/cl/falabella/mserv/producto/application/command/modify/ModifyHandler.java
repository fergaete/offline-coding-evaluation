package cl.falabella.mserv.producto.application.command.modify;

import cl.falabella.mserv.producto.application.command.CommandHandlerInterface;
import cl.falabella.mserv.producto.application.service.ProductService;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class ModifyHandler implements CommandHandlerInterface {

    private final ProductService _productService;

    public ModifyHandler(ProductService productService) {
        _productService = productService;
    }

    public ProductDTO handler(ModifyCommand command) {
        log.info("ModifyHandler.handler() called");
        log.info("ModifyHandler.handler() command: {}", command);
        log.info("ModifyHandler.handler() find product");

        Product product = _productService.findOneByProductId(command.productId());

        log.info("ModifyHandler.handler() product: {}", product);

        product.modify(
                command.name(),
                command.brand(),
                command.size(),
                command.price(),
                command.image()
        );

        _productService.save(product);

        log.info("ModifyHandler.handler() product modify: {}", product);

        return _productService.findBySKU(product.getSku());
    }
}
