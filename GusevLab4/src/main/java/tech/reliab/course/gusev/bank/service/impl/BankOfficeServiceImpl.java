package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankAtm;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;
import tech.reliab.course.gusev.bank.service.BankOfficeService;
import tech.reliab.course.gusev.bank.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOfficeServiceImpl implements BankOfficeService {

    @Override
    public List<Employee> getWorkers(BankOffice bankOffice) {
        return bankOffice.getLstEmployees();
    }
    @Override
    public List<BankAtm> getAtms(BankOffice bankOffice) {
        return bankOffice.getLstBankAtm();
    }

    @Override
    public Boolean addWorker(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee !=null) {
            return bankOffice.getLstEmployees().add(employee);
        }
        return false;
    }
    @Override
    public Boolean deleteWorker(BankOffice bankOffice, Employee employee) {
        if (bankOffice != null && employee !=null) {
            return bankOffice.getLstEmployees().remove(employee);
        }
        return false;
    }
    @Override
    public Boolean addAtm(BankOffice bankOffice, BankAtm atm) {
        if (bankOffice != null && atm !=null) {
            Boolean res = bankOffice.getLstBankAtm().add(atm);
            if (res) {
                bankOffice.setNumberAtm(bankOffice.getNumberAtm() + 1);
            }
            return res;
        }
        return false;
    }
    @Override
    public Boolean deleteAtm(BankOffice bankOffice, BankAtm atm) {
        if (bankOffice != null && atm !=null) {
            Boolean res = bankOffice.getLstBankAtm().add(atm);
            if (res) {
                bankOffice.setNumberAtm(bankOffice.getNumberAtm() - 1);
            }
            return res;
        }
        return false;
    }

    @Override
    public BankOffice create(String address, Boolean isWork,
                             Boolean permissionAtm, Integer numberAtm, Boolean isCreditPermission, Boolean isCashIssue,
                             Boolean isCashDeposit, BigDecimal money, BigDecimal rentalPrice
    ) {
//        String[] wordsAddress = address.split(" ");
        String name = "Офис " + address + " №";
        BankOffice newBankOffice = new BankOffice(name, address, isWork,
                permissionAtm, numberAtm, isCreditPermission, isCashIssue,
                isCashDeposit, money, rentalPrice);
        newBankOffice.setName(name + String.valueOf(newBankOffice.getId()));
        return newBankOffice;
    }

    @Override
    public Boolean updateAllMoney(BankOffice bankOffice, Bank bank) {
        if (bankOffice != null && bank != null) {
            bankOffice.setMoney(BigDecimal.valueOf(bank.getMoney().doubleValue()));
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateRandRentalCost(BankOffice bankOffice) {
        if (bankOffice != null) {
            bankOffice.setRentalPrice(BigDecimal.valueOf(Constants.RENTAL_OFFICE_MAX_PRICE * Math.random()));
            return true;
        }
        return false;
    }
}
