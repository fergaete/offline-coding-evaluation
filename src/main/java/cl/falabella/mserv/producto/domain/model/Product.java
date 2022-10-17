package cl.falabella.mserv.producto.domain.model;

import cl.falabella.mserv.producto.domain.exception.ProductAlreadyExistException;
import cl.falabella.mserv.producto.domain.repository.UniqueSKUSpecificationInterface;
import cl.falabella.mserv.producto.domain.vo.Name;
import cl.falabella.mserv.producto.domain.vo.Brand;
import cl.falabella.mserv.producto.domain.vo.Price;
import cl.falabella.mserv.producto.domain.vo.SKU;
import cl.falabella.mserv.producto.domain.vo.Size;
import cl.falabella.mserv.producto.infrastructure.jpa.converter.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "Product")
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
    @ColumnDefault("random_uuid()")
    private UUID id;

    @NotNull(message = "El atributo Name no puede ser vácio.")
    @Convert(converter = NameAttributeConverter.class)
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)", length = 50)
    private Name name;

    @NotNull(message = "El atributo Brand no puede ser vácio.")
    @Convert(converter = BrandAttributeConverter.class)
    @Column(name = "brand", nullable = false, columnDefinition = "varchar(50)", length = 50)
    private Brand brand;

    @Convert(converter = SizeAttributeConverter.class)
    @Column(name = "size", nullable = false, columnDefinition = "varchar(50)", length = 50)
    private Size size;

    @NotNull(message = "El atributo SKU no puede ser vácio.")
    @Convert(converter = SKUAttributeConverter.class)
    @Column(name = "sku", nullable = false, columnDefinition = "varchar(13)", length = 13, unique = true)
    private SKU sku;

    @NotNull(message = "El atributo price no puede ser vácio.")
    @Convert(converter = PriceAttributeConverter.class)
    @Column(name = "price")
    private Price price;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(
            Name name,
            Brand brand,
            Size size,
            SKU sku,
            Price price,
            UniqueSKUSpecificationInterface uniqueSKUSpecification
    ) {

        if (uniqueSKUSpecification.isSatisfiedBy(sku)) {
            throw new ProductAlreadyExistException(
                    String.format("El Producto con SKU %s, ya existe.", sku.toString())
            );
        }

        this.name = name;
        this.brand = brand;
        this.size = size;
        this.sku = sku;
        this.price = price;
        this.createdAt = java.time.LocalDateTime.now();
    }

    public void modify(
            Name name,
            Brand brand,
            Size size,
            Price price
    ) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.updatedAt = java.time.LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id.toString() +
                ", name=" + name.toString() +
                ", brand=" + brand.toString() +
                ", size=" + size.toString() +
                ", price=" + price.toString() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
