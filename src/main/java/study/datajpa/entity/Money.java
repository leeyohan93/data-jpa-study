package study.datajpa.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Money {

    private static final String NUMBER_REGEX = "[0-9]";
    private static final String NOT_NUMBER_REGEX = "[^0-9]";
    private static final String EMPTY = "";

    private int value;
    private String unit;

    public static Money valueOf(String value) {
        return new Money(Integer.parseInt(value.replaceAll(NOT_NUMBER_REGEX, EMPTY)),
                value.replaceAll(NUMBER_REGEX, EMPTY));

    }

    public Money(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }
}
