package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    public Employee create(String fullName, Date birthday, String position, Bank bank, Boolean remoteWork,
                           BankOffice bankOffice, Boolean creditPossibility, BigDecimal salary);

    public Boolean updateWork(Employee employee, BankOffice bankOffice, Bank bank, String position, BigDecimal salary);

    public List<Employee> getAll();

    public List<Employee> getWorkerList(Integer id);

}
