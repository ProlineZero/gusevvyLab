package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;
import tech.reliab.course.gusev.bank.service.EmployeeService;

import java.math.BigDecimal;
import java.util.*;

public class EmployeeServiceImpl implements EmployeeService {

    Map<Integer, Employee> employeeTable = new HashMap<>();

    Map<Integer, List<Employee>> employeeByBankId = new HashMap<>();

    @Override
    public List<Employee> getWorkerList(Integer id) {
        return employeeByBankId.get(id);
    }



    @Override
    public Employee create(String fullName, Date birthday, String position, Bank bank, Boolean remoteWork,
                           BankOffice bankOffice, Boolean creditPossibility, BigDecimal salary) {
        Employee newEmployee = new Employee(fullName, birthday, position,
                bank, remoteWork, bankOffice, creditPossibility, salary);
        employeeTable.put(newEmployee.getId(), newEmployee);
        if (employeeByBankId.get(bank.getId()) == null) {
            employeeByBankId.put(bank.getId(), new ArrayList<Employee>());
        }
        employeeByBankId.get(bank.getId()).add(newEmployee);

        return newEmployee;
    }

    @Override
    public Boolean updateWork(Employee employee, BankOffice bankOffice, Bank bank, String position, BigDecimal salary) {
        if (employee != null && bankOffice != null && bank != null && position != null && salary != null) {
            employee.setBank(bank);
            employee.setPosition(position);
            employee.setSalary(salary);
            employee.setOffice(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<Employee>(employeeTable.values());
    }
}
