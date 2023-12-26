package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankAtm;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface BankOfficeService {
    public BankOffice create(String address, Boolean isWork,
                             Boolean permissionAtm, Integer numberAtm, Boolean isCreditPermission, Boolean isCashIssue,
                             Boolean isCashDeposit, BigDecimal money, BigDecimal rentalPrice);

    public List<Employee> getWorkers(BankOffice bankOffice);
    public List<BankAtm> getAtms(BankOffice bankOffice);
    public Boolean updateAllMoney(BankOffice bankOffice, Bank bank);

    public Boolean updateRandRentalCost(BankOffice bankOffice);

    public Boolean addWorker(BankOffice bankOffice, Employee employee);

    public Boolean deleteWorker(BankOffice bankOffice, Employee employee);

    public Boolean addAtm(BankOffice bankOffice, BankAtm atm);

    public Boolean deleteAtm(BankOffice bankOffice, BankAtm atm);

}
