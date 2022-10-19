package cl.falabella.mserv.producto.infrastructure.jpa.repository;

import cl.falabella.mserv.producto.domain.model.Product;
import cl.falabella.mserv.producto.domain.vo.SKU;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, UUID> {

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllBySku(@NonNull SKU sku);

    Optional<Product> findBySku(SKU sku);
}
