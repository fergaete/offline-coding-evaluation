package cl.falabella.mserv.producto.application.command.delete;

import cl.falabella.mserv.producto.application.command.CommandInterface;
import cl.falabella.mserv.producto.domain.model.ProductId;

public final class DeleteCommand implements CommandInterface {

    private final ProductId productId;

    public DeleteCommand(String id) {
        this.productId = new ProductId(id);
    }

    public ProductId productId() {
        return productId;
    }
}