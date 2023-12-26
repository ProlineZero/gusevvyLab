package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.BankAtm;
import ru.bstu.course.gusev.bank.entity.BankOffice;
import ru.bstu.course.gusev.bank.entity.Employee;
import ru.bstu.course.gusev.bank.service.AtmService;
import ru.bstu.course.gusev.bank.service.EmployeeService;
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
    private EmployeeService employeeService;
    private AtmService atmService;

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

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setAtmService(AtmService atmService) {
        this.atmService = atmService;
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

    private List<BankAtm> getAllOfficeAtms(int id) {
        return atmsByOfficeId.get(id);
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

    @Override
    public boolean isSuitableBankOffice(BankOffice bankOffice, BigDecimal money) throws Exception {
        if (bankOffice.isWorking() && bankOffice.isCashWithdrawalAvailable()
            && bankOffice.getTotalMoney().compareTo(money) >= 0) {
            List<BankAtm> bankAtmSuitable = getSuitableBankAtmInOffice(bankOffice, money);
            if (bankAtmSuitable.isEmpty()) {
                return false;
            }

            List<Employee> employeesSuitable = getSuitableEmployeeInOffice(bankOffice);
            if (employeesSuitable.isEmpty()) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public List<BankAtm> getSuitableBankAtmInOffice(BankOffice bankOffice, BigDecimal money) {
        List<BankAtm> bankAtmByOffice = getAllOfficeAtms(bankOffice.getId());
        List<BankAtm> suitableBankAtm = new ArrayList<>();

        for (BankAtm bankAtm : bankAtmByOffice) {
            if (atmService.isAtmSuitable(bankAtm, money)) {
                suitableBankAtm.add(bankAtm);
            }
        }

        return suitableBankAtm;    }

    @Override
    public List<Employee> getSuitableEmployeeInOffice(BankOffice bankOffice) throws Exception {
        List<Employee> employees = getAllEmployeesByOfficeId(bankOffice.getId());
        List<Employee> suitableEmployee = new ArrayList<>();

        for (Employee employee : employees) {
            if (employeeService.isEmployeeSuitable(employee)) {
                suitableEmployee.add(employee);
            }
        }

        return suitableEmployee;    }
}
