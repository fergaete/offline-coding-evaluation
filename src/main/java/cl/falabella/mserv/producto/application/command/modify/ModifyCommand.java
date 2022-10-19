package cl.falabella.mserv.producto.application.command.modify;

import cl.falabella.mserv.producto.application.command.CommandInterface;
import cl.falabella.mserv.producto.domain.model.ProductId;
import cl.falabella.mserv.producto.domain.vo.*;
import lombok.NonNull;
import lombok.ToString;

@ToString
public final class ModifyCommand implements CommandInterface  {

    private final @NonNull ProductId productId;
    private final @NonNull Name name;
    private final @NonNull Brand brand;
    private final @NonNull Size size;
    private final @NonNull Price price;
    private final @NonNull Image image;

    public ModifyCommand(
            @NonNull String id,
            @NonNull String name,
            @NonNull String brand,
            @NonNull String size,
            @NonNull Double price,
            @NonNull String image
    ) {
        this.productId = new ProductId(id);
        this.name = new Name(name);
        this.brand = new Brand(brand);
        this.size = new Size(size);
        this.price = new Price(price);
        this.image = new Image(image);
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

    public Image image() {
        return image;
    }
}