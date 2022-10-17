package cl.falabella.mserv.producto.application.service;

import cl.falabella.mserv.producto.domain.DTO.PaginatedRepresentation;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.converter.ProductConverter;
import cl.falabella.mserv.producto.domain.exception.EntityNotFoundException;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.model.ProductId;
import cl.falabella.mserv.producto.domain.repository.ProductRepositoryInterface;
import cl.falabella.mserv.producto.domain.vo.SKU;
import cl.falabella.mserv.producto.infrastructure.jpa.repository.ProductJPARepository;
import cl.falabella.mserv.producto.infrastructure.shared.HateoasUtils;
import cl.falabella.mserv.producto.presentation.controller.FindAllController;
import cl.falabella.mserv.producto.presentation.controller.constant.PaginationSwagger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
public class ProductService implements ProductRepositoryInterface {

    @Value("${hateoas.without.port}")
    private boolean WITHOUT_PORT;

    private final ProductJPARepository _productJPARepository;
    private final ProductConverter _productConverter;

    public ProductService(ProductJPARepository productJPARepository, ProductConverter productConverter) {
        _productJPARepository = productJPARepository;
        _productConverter = productConverter;
    }

    private Map<String, String> _createLinks(Integer page, Integer size, Integer lastPage) {
        Link self  = linkTo(methodOn(FindAllController.class).findAll(page, size)).withSelfRel();
        Link first = linkTo(methodOn(FindAllController.class).findAll(PaginatedRepresentation.DEFAULT_PAGE, size)).withRel(PaginationSwagger.FIRST_ELEMENT);
        Link last  = linkTo(methodOn(FindAllController.class).findAll(lastPage, size)).withRel(PaginationSwagger.LAST_ELEMENT);

        Map<String, String> links = new LinkedHashMap<>();
        links.put(self.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(self.getHref()) : self.getHref());
        links.put(first.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(first.getHref()) : first.getHref());
        links.put(last.getRel().value(), WITHOUT_PORT ? HateoasUtils.removePort(last.getHref()) : last.getHref());

        return links;
    }

    @Override
    public PaginatedRepresentation findAll(Integer page, Integer size) {
        log.info("Obteniendo listado de productos.");
        try {
            Page<Product> products = _productJPARepository.findAll(PageRequest.of(page, size));
            Map<String, Object> productos = new LinkedHashMap<>();
            productos.put("all", products.get().map(_productConverter::convert).collect(Collectors.toList()));

            return PaginatedRepresentation
                    .builder()
                    .page(products.getNumber())
                    .limit(size)
                    .pages(PaginatedRepresentation.totalPages(products.getTotalPages()))
                    .total(products.getTotalElements())
                    ._links(_createLinks(page, size, PaginatedRepresentation.totalPages(products.getTotalPages())))
                    ._embedded(productos)
                    .build();

        } catch (Exception e) {
            log.error("Error al obtener el listado de productos", e);
        }

        return PaginatedRepresentation.builder().build();
    }

    @Override
    public List<Product> findAllBySKU(SKU sku) {
        return _productJPARepository.findAllBySku(sku);
    }

    @Override
    public Product findOneByProductId(ProductId productId) throws EntityNotFoundException {
        return _productJPARepository.findById(UUID.fromString(productId.toString()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se ha encontrado un producto con Id: %s", productId.toString())));
    }

    @Override
    public ProductDTO findById(ProductId id) throws EntityNotFoundException {
        Product product = _productJPARepository.findById(UUID.fromString(id.toString()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se ha encontrado un producto con Id: %s", id.toString())));

        return _productConverter.convert(product);
    }

    @Override
    public ProductDTO findBySKU(SKU sku) throws EntityNotFoundException {
        Product product = _productJPARepository.findBySku(sku)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No se ha encontrado un producto con SKU: %s", sku.toString())));

        return _productConverter.convert(product);
    }

    @Override
    public void save(Product product) {
        _productJPARepository.saveAndFlush(product);
    }

    @Override
    public void delete(Product product) {
        _productJPARepository.delete(product);
    }
}