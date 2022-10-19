package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Name;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class NameAttributeConverter implements AttributeConverter<Name, String> {

    @Override
    public String convertToDatabaseColumn(Name attribute) {
        log.info("NameAttributeConverter::convertToDatabaseColumn() attribute: {}", attribute);
        return attribute.toString();
    }

    @Override
    public Name convertToEntityAttribute(String data) {
        log.info("NameAttributeConverter::convertToEntityAttribute() data: {}", data);
        return data == null ? null : new Name(data);
    }
}
