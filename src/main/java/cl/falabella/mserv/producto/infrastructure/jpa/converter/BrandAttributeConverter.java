package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Brand;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class BrandAttributeConverter implements AttributeConverter<Brand, String> {

    @Override
    public String convertToDatabaseColumn(Brand attribute) {
        log.info("BrandAttributeConverter::convertToDatabaseColumn() attribute: " + attribute);
        return attribute.toString();
    }

    @Override
    public Brand convertToEntityAttribute(String data) {
        log.info("BrandAttributeConverter::convertToEntityAttribute() data: " + data);
        return data == null ? null : new Brand(data);
    }
}