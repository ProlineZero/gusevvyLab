package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.BankAtm;
import ru.bstu.course.gusev.bank.entity.BankOffice;
import ru.bstu.course.gusev.bank.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface BankOfficeService extends Crud<BankOffice> {

    boolean installAtm(int id, BankAtm bankAtm);

    boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm);

    boolean depositMoney(BankOffice bankOffice, BigDecimal amount);

    boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount);

    void printData(int id);

    List<Employee> getAllEmployeesByOfficeId(int id);

    boolean addEmployee(int id, Employee employee);

    boolean removeEmployee(BankOffice bankOffice, Employee employee);

    boolean isSuitableBankOffice(BankOffice bankOffice, BigDecimal money) throws Exception;

    List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, BigDecimal money);

    List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) throws Exception;

    void setEmployeeService(EmployeeService employeeService);

    void setAtmService(AtmService atmService);
}
