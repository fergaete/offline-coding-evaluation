package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.UUIDv4;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class UUIDv4AttributeConverter implements AttributeConverter<UUIDv4, String> {

    @Override
    public String convertToDatabaseColumn(UUIDv4 attribute) {
        log.info("UUIDv4AttributeConverter::convertToDatabaseColumn() attribute: {}", attribute);
        return attribute.toString();
    }

    @Override
    public UUIDv4 convertToEntityAttribute(String data) {
        log.info("UUIDv4AttributeConverter::convertToEntityAttribute() data: {}", data);
        return data == null ? null : new UUIDv4(data);
    }
}
