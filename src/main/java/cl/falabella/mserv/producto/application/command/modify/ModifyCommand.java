package cl.falabella.mserv.producto.application.command.modify;

import cl.falabella.mserv.producto.application.command.CommandInterface;
import cl.falabella.mserv.producto.domain.model.ProductId;
import cl.falabella.mserv.producto.domain.vo.*;

public final class ModifyCommand implements CommandInterface  {

    private final ProductId productId;
    private final Name name;
    private final Brand brand;
    private final Size size;
    private final Price price;

    public ModifyCommand(
            String id,
            String name,
            String brand,
            String size,
            Double price
    ) {
        this.productId = new ProductId(id);
        this.name = new Name(name);
        this.brand = new Brand(brand);
        this.size = new Size(size);
        this.price = new Price(price);
    }

    public ProductId productId() {
        return productId;
    }

    public Name name() {
        return name;
    }

    public Brand brand() {
        return brand;
    }

    public Size size() {
        return size;
    }

    public Price price() {
        return price;
    }
}