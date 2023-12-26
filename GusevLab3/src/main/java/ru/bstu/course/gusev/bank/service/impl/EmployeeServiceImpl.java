package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.BankOffice;
import ru.bstu.course.gusev.bank.entity.Employee;
import ru.bstu.course.gusev.bank.validator.EmployeeValidator;
import ru.bstu.course.gusev.bank.service.BankOfficeService;
import ru.bstu.course.gusev.bank.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> employees = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    public EmployeeServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public Employee findById(Integer id) {

        Employee employee = employees.get(id);

        if (employee == null) {
            System.err.println("Employee with id " + id + " is not found");
        }

        return employee;
    }

    @Override
    public List<Employee> fetchAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee create(Employee employee) {

        if (EmployeeValidator.validateCreate(employee)) return null;

        Employee newEmployee = new Employee(employee);

        employees.put(newEmployee.getId(), newEmployee);
        bankOfficeService.addEmployee(newEmployee.getBankOffice().getId(), newEmployee);

        return newEmployee;
    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public boolean transferEmployee(Employee employee, BankOffice bankOffice) {
        return false;
    }

    @Override
    public boolean isEmployeeSuitable(Employee employee) {
        return employee.isCreditAvailable();
    }
}
