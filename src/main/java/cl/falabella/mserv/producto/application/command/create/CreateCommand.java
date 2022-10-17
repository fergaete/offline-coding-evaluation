package cl.falabella.mserv.producto.application.command.create;

import cl.falabella.mserv.producto.application.command.CommandInterface;
import cl.falabella.mserv.producto.domain.vo.*;

public final class CreateCommand implements CommandInterface  {

    private final Name name;
    private final Brand brand;
    private final Size size;
    private final SKU sku;
    private final Price price;

    public CreateCommand(
            String name,
            String brand,
            String size,
            String sku,
            Double price
    ) {
        this.name = new Name(name);
        this.brand = new Brand(brand);
        this.size = new Size(size);
        this.sku = new SKU(sku);
        this.price = new Price(price);
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

    public SKU sku() {
        return sku;
    }

    public Price price() {
        return price;
    }
}