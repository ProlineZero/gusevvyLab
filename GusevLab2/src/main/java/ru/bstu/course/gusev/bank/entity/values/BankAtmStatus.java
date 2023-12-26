package ru.bstu.course.gusev.bank.entity.values;

public enum BankAtmStatus {

    WORKING("working"), NOT_WORKING("not working"), NOT_ENOUGH_MONEY("not enough money");

    private String value;

    BankAtmStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
