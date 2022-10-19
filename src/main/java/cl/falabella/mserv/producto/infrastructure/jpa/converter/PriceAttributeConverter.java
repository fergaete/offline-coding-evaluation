package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Price;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Slf4j
@Converter(autoApply = true)
public class PriceAttributeConverter implements AttributeConverter<Price, Double> {

    @Override
    public Double convertToDatabaseColumn(Price attribute) {
        log.info("PriceAttributeConverter::convertToDatabaseColumn() attribute: {}", attribute);
        return attribute.toDouble();
    }

    @Override
    public Price convertToEntityAttribute(Double data) {
        log.info("PriceAttributeConverter::convertToEntityAttribute() data: {}", data);
        return new Price(data);
    }
}