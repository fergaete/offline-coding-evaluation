package cl.falabella.mserv.producto.application.command.delete;

import cl.falabella.mserv.producto.application.command.CommandHandlerInterface;
import cl.falabella.mserv.producto.application.service.ProductService;
import cl.falabella.mserv.producto.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteHandler implements CommandHandlerInterface {

    private final ProductService _productService;

    public DeleteHandler(ProductService productService) {
        _productService = productService;
    }

    public void handler(DeleteCommand command) {
        Product product = _productService.findOneByProductId(command.productId());
        _productService.delete(product);
    }
}