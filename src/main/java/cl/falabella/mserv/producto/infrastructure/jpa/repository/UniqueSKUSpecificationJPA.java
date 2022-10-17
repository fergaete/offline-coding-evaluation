package cl.falabella.mserv.producto.infrastructure.jpa.repository;

import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.repository.ProductRepositoryInterface;
import cl.falabella.mserv.producto.domain.repository.UniqueSKUSpecificationInterface;
import cl.falabella.mserv.producto.domain.vo.SKU;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniqueSKUSpecificationJPA implements UniqueSKUSpecificationInterface  {

    private final ProductRepositoryInterface _productRepository;

    public UniqueSKUSpecificationJPA(ProductRepositoryInterface productRepository) {
        _productRepository = productRepository;
    }

    @Override
    public boolean isSatisfiedBy(SKU sku) {
        List<Product> products = _productRepository.findAllBySKU(sku);

        return (products.size() >= 1);
    }
}