package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.Main;
import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.BankAtm;
import tech.reliab.course.gusev.bank.entity.BankOffice;
import tech.reliab.course.gusev.bank.entity.Employee;
import tech.reliab.course.gusev.bank.service.AtmService;
import tech.reliab.course.gusev.bank.utils.AtmStatus;
import tech.reliab.course.gusev.bank.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmServiceImpl implements AtmService {
    Map<Integer, BankAtm> atmTable = new HashMap<Integer, BankAtm> ();

    Map<Integer, List<BankAtm>> atmByBankId = new HashMap<Integer, List<BankAtm>>();

    @Override
    public BankAtm create(Bank refBank, BankOffice locate, Employee employee, AtmStatus status,
                          Boolean isCashIssue, Boolean isCashDeposit, BigDecimal maintenanceCost) {

        BankAtm newBankAtm = new BankAtm(refBank, locate, employee, "Неопределено",
                status, isCashIssue, isCashDeposit, locate.getMoney(), maintenanceCost);
        String name = "Банкомат офиса "+ locate.getName() + " с id - " + newBankAtm.getId();
        newBankAtm.setName(name);
        atmTable.put(newBankAtm.getId(), newBankAtm);
        if (atmByBankId.get(refBank.getId()) == null) {
            atmByBankId.put(refBank.getId(), new ArrayList<BankAtm>());
        }
        atmByBankId.get(refBank.getId()).add(newBankAtm);
        return newBankAtm;
    }

    @Override
    public Boolean updateChangeMoney(BankAtm bankAtm) {
        if (bankAtm != null) {
            bankAtm.setMoney(BigDecimal.valueOf(bankAtm.getLocate().getMoney().doubleValue() * Math.random()));
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateChangeOffice(BankAtm bankAtm, BankOffice bankOffice) {
        if (bankAtm != null && bankOffice != null) {
            bankAtm.setLocate(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateSetStatus(BankAtm bankAtm, AtmStatus atmStatus) {
        if (bankAtm != null && atmStatus != null) {
            bankAtm.setStatus(atmStatus);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateRandMaintenceCost(BankAtm bankAtm) {
        if (bankAtm != null) {
            bankAtm.setMaintenanceCost(BigDecimal.valueOf(Constants.MAINTENANCE_COST_OF_ATM * Math.random()));
            return true;
        }
        return false;
    }
}
