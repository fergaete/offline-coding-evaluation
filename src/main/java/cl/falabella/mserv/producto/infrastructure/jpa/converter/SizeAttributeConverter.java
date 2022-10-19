package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Size;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class SizeAttributeConverter implements AttributeConverter<Size, String> {

    @Override
    public String convertToDatabaseColumn(Size attribute) {
        log.info("SizeAttributeConverter::convertToDatabaseColumn() attribute: {}", attribute);
        return attribute.toString();
    }

    @Override
    public Size convertToEntityAttribute(String data) {
        log.info("SizeAttributeConverter::convertToEntityAttribute() data: {}", data);
        return data == null ? null : new Size(data);
    }
}