package study.datajpa.entity;

public class Money {

    private int value;

    private String unit;

    public Money(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return value + unit;
    }
}
