package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.BankOffice;
import ru.bstu.course.gusev.bank.entity.Employee;

public interface EmployeeService extends Crud<Employee> {

    boolean transferEmployee(Employee employee, BankOffice bankOffice);

    boolean isEmployeeSuitable(Employee employee);
}
