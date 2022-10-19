package cl.falabella.mserv.producto.domain.converter;

import cl.falabella.mserv.producto.domain.DTO.PaginatedRepresentation;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.exception.ConversionException;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.infrastructure.shared.HateoasUtils;
import cl.falabella.mserv.producto.presentation.controller.FindAllController;
import cl.falabella.mserv.producto.presentation.controller.FindByIdController;
import cl.falabella.mserv.producto.presentation.controller.PostController;
import cl.falabella.mserv.producto.presentation.controller.PutController;
import cl.falabella.mserv.producto.presentation.controller.form.CreateForm;
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
        Link all  = linkTo(methodOn(FindAllController.class).findAll(PaginatedRepresentation.DEFAULT_PAGE, PaginatedRepresentation.DEFAULT_SIZE)).withRel("all");
        Link self = linkTo(methodOn(FindByIdController.class).findById(product.getId().toString())).withSelfRel();
        Link post  = linkTo(methodOn(PostController.class).post(new CreateForm())).withRel("post");
        Link put  = linkTo(methodOn(FindByIdController.class).findById(product.getId().toString())).withRel("put");
        Link delete  = linkTo(methodOn(FindByIdController.class).findById(product.getId().toString())).withRel("delete");

        Map<String, String> links = new LinkedHashMap<>();
        links.put(all.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(all.getHref()) : all.getHref());
        links.put(self.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(self.getHref()) : self.getHref());
        links.put(post.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(post.getHref()) : post.getHref());
        links.put(put.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(put.getHref()) : put.getHref());
        links.put(delete.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(delete.getHref()) : delete.getHref());

        return links;
    }

    private static Map<String, Object> _createEmbeddedsObject(Product product) {
        return new LinkedHashMap<>();
    }
}