package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.List;

public interface BankService extends Crud<Bank>, Datable {

    void setBankOfficeService(BankOfficeService bankOfficeService);

    void setClientService(ClientService bankOfficeService);

    List<BankOffice> getAllOfficesByBankId(int id);

    boolean addOffice(int bankId, BankOffice bankOffice);

    boolean removeOffice(int bankId, BankOffice bankOffice);

    boolean addEmployee(Bank bank, Employee employee);

    boolean removeEmployee(Bank bank, Employee employee);

    boolean addClient(int id, Client client);

    boolean removeClient(Bank bank, Client client);

    BigDecimal calculateInterestRate(Bank bank);

    boolean depositMoney(int id, BigDecimal amount);

    boolean withdrawMoney(int id, BigDecimal amount);

    boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws Exception;

    List<Bank> getBanksSuitable(BigDecimal sum, int countMonth) throws Exception;

    boolean isBankSuitable(Bank bank, BigDecimal money) throws Exception;

    List<BankOffice> getBankOfficeSuitableInBank(Bank bank, BigDecimal money) throws Exception;
}
