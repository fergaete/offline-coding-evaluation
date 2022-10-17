package cl.falabella.mserv.producto.domain.repository;

import cl.falabella.mserv.producto.domain.DTO.PaginatedRepresentation;
import cl.falabella.mserv.producto.domain.DTO.ProductDTO;
import cl.falabella.mserv.producto.domain.exception.EntityNotFoundException;
import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.model.ProductId;
import cl.falabella.mserv.producto.domain.vo.SKU;

import java.util.List;

public interface ProductRepositoryInterface {

    PaginatedRepresentation findAll(Integer page, Integer size);

    List<Product> findAllBySKU(SKU sku);

    ProductDTO findById(ProductId id) throws EntityNotFoundException;

    ProductDTO findBySKU(SKU sku) throws EntityNotFoundException;

    Product findOneByProductId(ProductId productId) throws EntityNotFoundException;

    void save(Product product);

    void delete(Product product);
}
