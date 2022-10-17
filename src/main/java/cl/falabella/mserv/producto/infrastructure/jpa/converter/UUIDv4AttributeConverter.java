package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.UUIDv4;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UUIDv4AttributeConverter implements AttributeConverter<UUIDv4, String> {

    @Override
    public String convertToDatabaseColumn(UUIDv4 attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public UUIDv4 convertToEntityAttribute(String data) {
        return data == null ? null : new UUIDv4(data);
    }
}
