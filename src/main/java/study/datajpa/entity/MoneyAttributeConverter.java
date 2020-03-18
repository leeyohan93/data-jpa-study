package study.datajpa.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyAttributeConverter implements AttributeConverter<Money, String> {
    @Override
    public String convertToDatabaseColumn(Money attribute) {
        return attribute.toString();
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        int value = Integer.parseInt(dbData.substring(0, dbData.length() - 1));
        return new Money(value, dbData.substring(dbData.length()));
    }
}
