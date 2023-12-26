package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.Employee;

public class EmployeeValidator {
    public static boolean validateCreate(Employee employee) {

        if (employee == null) {
            return true;
        }

        if (employee.getSalary().signum() < 0) {

            System.err.println("Error: Employee - salary must be non-negative");
            return true;
        }

        return false;
    }
}
