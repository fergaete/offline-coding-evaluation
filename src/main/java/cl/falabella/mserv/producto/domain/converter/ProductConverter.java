package cl.falabella.mserv.producto.domain.converter;

import cl.falabella.mserv.producto.domain.DTO.PaginatedRepresentation;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.exception.ConversionException;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.infrastructure.shared.HateoasUtils;
import cl.falabella.mserv.producto.presentation.controller.FindAllController;
import cl.falabella.mserv.producto.presentation.controller.FindByIdController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Component
public class ProductConverter implements Converter<Product, ProductDTO> {

    @Value("${hateoas.without.port}")
    private static boolean WITHOUT_PORT;

    @Override
    public ProductDTO convert(Product product) {
        log.info(String.format("Convirtiendo objeto %s", product.toString()));
        try {
            return ProductDTO.builder()
                    .id(product.getId())
                    .sku(product.getSku().toString())
                    .name(product.getName().toString())
                    .brand(product.getBrand().toString())
                    .size(product.getSize().toString())
                    .price(product.getPrice().toDouble())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    ._links(_createLinks(product))
                    ._embedded(_createEmbeddedsObject(product))
                    .build();
        } catch (Exception e) {
            throw new ConversionException("Error al convertir un producto", e);
        }
    }

    private static Map<String, String> _createLinks(Product product) {
        Link self       = linkTo(methodOn(FindByIdController.class).findById(product.getId().toString())).withSelfRel();
        Link get        = linkTo(methodOn(FindAllController.class).findAll(PaginatedRepresentation.DEFAULT_PAGE, PaginatedRepresentation.DEFAULT_SIZE)).withRel("products");

        Map<String, String> links = new LinkedHashMap<>();
        links.put(self.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(self.getHref()) : self.getHref());
        links.put(get.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(get.getHref()) : get.getHref());

        return links;
    }

    private static Map<String, Object> _createEmbeddedsObject(Product product) {
        return new LinkedHashMap<>();
    }
}