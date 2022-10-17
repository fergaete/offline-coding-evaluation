package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Size;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SizeAttributeConverter implements AttributeConverter<Size, String> {

    @Override
    public String convertToDatabaseColumn(Size attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Size convertToEntityAttribute(String data) {
        return data == null ? null : new Size(data);
    }
}