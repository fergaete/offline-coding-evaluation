package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.SKU;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SKUAttributeConverter implements AttributeConverter<SKU, String> {

    @Override
    public String convertToDatabaseColumn(SKU attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public SKU convertToEntityAttribute(String data) {
        return data == null ? null : new SKU(data);
    }
}