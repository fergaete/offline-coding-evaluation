package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Image;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class ImageAttributeConverter implements AttributeConverter<Image, String> {

    @Override
    public String convertToDatabaseColumn(Image attribute) {
        log.info("ImageAttributeConverter::convertToDatabaseColumn() attribute: " + attribute);
        return attribute.toString();
    }

    @Override
    public Image convertToEntityAttribute(String data) {
        log.info("ImageAttributeConverter::convertToEntityAttribute() data: " + data);
        return data == null ? null : new Image(data);
    }
}