package ru.bstu.course.gusev.bank.entity;

import lombok.Data;
import ru.bstu.course.gusev.bank.entity.values.EmployeePost;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Employee extends Person {

    private EmployeePost job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private BigDecimal salary;

    public Employee() {

        super();

        this.job = null;
        this.bank = null;
        this.isWorkingFromHome = false;
        this.bankOffice = null;
        this.isCreditAvailable = false;
        this.salary = new BigDecimal("0");    }

    public Employee(Employee employee) {

        super(employee.id, employee.name, employee.birthDate);
        this.job = employee.job;
        this.bank = new Bank(employee.bank);
        this.isWorkingFromHome = employee.isWorkingFromHome;
        this.bankOffice = new BankOffice(employee.bankOffice);
        this.isCreditAvailable = employee.isCreditAvailable;
        this.salary = employee.salary;
    }

    public Employee(
            String name, LocalDate birthDate, EmployeePost job, Bank bank, boolean isWorkingFromHome,
            BankOffice bankOffice, boolean isCreditAvailable, BigDecimal salary
    ) {

        super(name, birthDate);

        this.job = job;
        this.bank = bank;
        this.isWorkingFromHome = isWorkingFromHome;
        this.bankOffice = bankOffice;
        this.isCreditAvailable = isCreditAvailable;
        this.salary = salary;
    }

    @Override
    public String toString() {

        return """
            Employee:
                person = '%s',
                job = '%s',
                bank = '%s',
                isWorkingFromHome = '%s',
                bankOffice = '%s',
                isCreditAvailable = '%s',
                salary = '%.2f'
        """.formatted(
            super.toString(), getJob(), getBank().getName(), isWorkingFromHome(),
            getBankOffice(), isCreditAvailable(), getSalary()
        );
    }
}
