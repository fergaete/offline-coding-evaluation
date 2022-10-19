package cl.falabella.mserv.producto.application.command.create;

import cl.falabella.mserv.producto.application.command.CommandInterface;
import cl.falabella.mserv.producto.domain.vo.*;
import lombok.NonNull;
import lombok.ToString;

@ToString
public final class CreateCommand implements CommandInterface  {

    private final @NonNull Name name;
    private final @NonNull Brand brand;
    private final @NonNull Size size;
    private final @NonNull SKU sku;
    private final @NonNull Price price;
    private final @NonNull Image image;

    public CreateCommand(
            @NonNull String name,
            @NonNull String brand,
            @NonNull String size,
            @NonNull String sku,
            @NonNull Double price,
            @NonNull String image
    ) {
        this.name = new Name(name);
        this.brand = new Brand(brand);
        this.size = new Size(size);
        this.sku = new SKU(sku);
        this.price = new Price(price);
        this.image = new Image(image);
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

    public Image image() {
        return image;
    }
}