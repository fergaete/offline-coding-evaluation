package cl.falabella.mserv.producto.infrastructure.jpa.converter;

import cl.falabella.mserv.producto.domain.vo.Price;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PriceAttributeConverter implements AttributeConverter<Price, Double> {

    @Override
    public Double convertToDatabaseColumn(Price attribute) {
        return attribute.toDouble();
    }

    @Override
    public Price convertToEntityAttribute(Double data) {
        return new Price(data);
    }
}