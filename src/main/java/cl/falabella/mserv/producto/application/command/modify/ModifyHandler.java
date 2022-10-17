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

        Product product = _productService.findOneByProductId(command.productId());

        product.modify(
                command.name(),
                command.brand(),
                command.size(),
                command.price()
        );

        _productService.save(product);

        return _productService.findBySKU(product.getSku());
    }
}
