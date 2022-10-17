package cl.falabella.mserv.producto.domain.repository;

import cl.falabella.mserv.producto.domain.vo.SKU;

public interface UniqueSKUSpecificationInterface {

    public boolean isSatisfiedBy(SKU sku);

}