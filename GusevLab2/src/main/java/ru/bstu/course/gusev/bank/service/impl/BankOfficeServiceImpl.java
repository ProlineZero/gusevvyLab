package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.BankAtm;
import ru.bstu.course.gusev.bank.entity.BankOffice;
import ru.bstu.course.gusev.bank.entity.Employee;
import ru.bstu.course.gusev.bank.validator.BankOfficeValidator;
import ru.bstu.course.gusev.bank.service.BankOfficeService;
import ru.bstu.course.gusev.bank.service.BankService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOfficeServiceImpl implements BankOfficeService {

    public static int currentId;
    private final Map<Integer, BankOffice> bankOffices = new HashMap<>();
    private final Map<Integer, List<Employee>> employeesByOfficeId = new HashMap<>();
    private final Map<Integer, List<BankAtm>> atmsByOfficeId = new HashMap<>();
    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public BankOffice findById(Integer id) {

        BankOffice office = bankOffices.get(id);

        if (office == null) {
            System.err.println("Office with id " + id + " is not found");
        }

        return office;
    }

    @Override
    public List<BankOffice> fetchAll() {
        return new ArrayList<>(bankOffices.values());
    }

    @Override
    public BankOffice create(BankOffice bankOffice) {

        if (BankOfficeValidator.validateCreate(bankOffice)) return null;

        BankOffice newOffice = new BankOffice(bankOffice);

        bankOffices.put(bankOffice.getId(), newOffice);
        employeesByOfficeId.put(newOffice.getId(), new ArrayList<>());
        atmsByOfficeId.put(newOffice.getId(), new ArrayList<>());
        bankService.addOffice(newOffice.getBank().getId(), newOffice);

        return newOffice;
    }

    @Override
    public void delete(BankOffice entity) {

    }

    @Override
    public void update(BankOffice entity) {

    }

    @Override
    public boolean installAtm(int id, BankAtm bankAtm) {

        BankOffice bankOffice = findById(id);

        if (bankOffice != null && bankAtm != null) {

            if (BankOfficeValidator.validateInstallAtm(bankOffice)) return false;

            bankOffice.setAtmCount(bankOffice.getAtmCount() + 1);
            bankOffice.getBank().setAtmCount(bankOffice.getBank().getAtmCount() + 1);

            bankAtm.setBankOffice(bankOffice);
            bankAtm.setAddress(bankOffice.getAddress());
            bankAtm.setBank(bankOffice.getBank());

            List<BankAtm> officeAtms = atmsByOfficeId.get(bankOffice.getId());
            officeAtms.add(bankAtm);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAtm(BankOffice bankOffice, BankAtm bankAtm) {

        if (bankOffice != null && bankAtm != null) {

            final int newAtmCountOffice = bankOffice.getAtmCount() - 1;

            if (BankOfficeValidator.validateRemoveAtm(newAtmCountOffice)) {
                return false;
            }

            bankOffice.setAtmCount(newAtmCountOffice);

            return true;
        }

        return false;
    }

    @Override
    public boolean depositMoney(BankOffice bankOffice, BigDecimal amount) {

        if (BankOfficeValidator.validateDepositMoney(bankOffice, amount)) return false;

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().add(amount));

        return true;
    }

    @Override
    public boolean withdrawMoney(BankOffice bankOffice, BigDecimal amount) {

        if (BankOfficeValidator.validateWithdrawMoney(bankOffice, amount)) return false;

        bankOffice.setTotalMoney(bankOffice.getTotalMoney().subtract(amount));

        return true;
    }

    @Override
    public void printData(int id) {

        BankOffice bankOffice = findById(id);

        if (bankOffice == null) {
            return;
        }

        System.out.println("=====================");
        System.out.println(bankOffice);

        List<Employee> employees = getAllEmployeesByOfficeId(id);

        if (employees != null) {

            System.out.println("Employees:");
            employees.forEach(System.out::println);
        }

        List<BankAtm> atms = atmsByOfficeId.get(id);

        if (atms != null) {

            System.out.println("Atms:");
            atms.forEach(System.out::println);
        }

        System.out.println("=====================");
    }

    @Override
    public List<Employee> getAllEmployeesByOfficeId(int id) {
        return employeesByOfficeId.get(id);
    }

    @Override
    public boolean addEmployee(int id, Employee employee) {

        BankOffice bankOffice = findById(id);

        if (bankOffice != null && employee != null) {

            employee.setBankOffice(bankOffice);
            employee.setBank(bankOffice.getBank());

            List<Employee> officeEmployees = employeesByOfficeId.get(bankOffice.getId());
            officeEmployees.add(employee);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeEmployee(BankOffice bankOffice, Employee employee) {
        return bankOffice != null && employee != null;
    }
}
