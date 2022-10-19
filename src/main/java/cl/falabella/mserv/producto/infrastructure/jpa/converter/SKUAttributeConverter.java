package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.SKU;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class SKUAttributeConverter implements AttributeConverter<SKU, String> {

    @Override
    public String convertToDatabaseColumn(SKU attribute) {
        log.info("SKUAttributeConverter::convertToDatabaseColumn() attribute: {}", attribute);
        return attribute.value();
    }

    @Override
    public SKU convertToEntityAttribute(String data) {
        log.info("SKUAttributeConverter::convertToEntityAttribute() data: {}", data);
        return data == null ? null : new SKU(data);
    }
}