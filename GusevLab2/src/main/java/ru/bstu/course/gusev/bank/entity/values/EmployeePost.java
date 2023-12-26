package ru.bstu.course.gusev.bank.entity.values;

public enum EmployeePost {

    DIRECTOR("director"), OPERATOR("operator"), OFFICE_EMPLOYEE("office employee");

    private final String value;

    EmployeePost(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
