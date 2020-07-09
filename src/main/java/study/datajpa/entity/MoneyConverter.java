package study.datajpa.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, String> {

    @Override
    public String convertToDatabaseColumn(Money attribute) {
        return attribute.toString();
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        Money money = Money.valueOf(dbData);
        return money;
    }
}

