package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Brand;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BrandAttributeConverter implements AttributeConverter<Brand, String> {

    @Override
    public String convertToDatabaseColumn(Brand attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Brand convertToEntityAttribute(String data) {
        return data == null ? null : new Brand(data);
    }
}