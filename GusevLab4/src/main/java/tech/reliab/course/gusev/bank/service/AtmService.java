package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankAtm;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;
import tech.reliab.course.gusev.bank.utils.AtmStatus;

import java.math.BigDecimal;

public interface AtmService {
    public BankAtm create(Bank refBank, BankOffice locate, Employee employee, AtmStatus status,
                          Boolean isCashIssue, Boolean isCashDeposit, BigDecimal maintenanceCost);

    public Boolean updateChangeMoney(BankAtm bankAtm);
    public Boolean updateSetStatus(BankAtm bankAtm, AtmStatus atmStatus);
    public Boolean updateChangeOffice(BankAtm bankAtm, BankOffice bankOffice);

    public Boolean updateRandMaintenceCost(BankAtm bankAtm);

}
