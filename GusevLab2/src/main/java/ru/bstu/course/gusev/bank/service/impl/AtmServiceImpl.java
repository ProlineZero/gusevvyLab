package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.BankAtm;
import ru.bstu.course.gusev.bank.validator.AtmValidator;
import ru.bstu.course.gusev.bank.service.AtmService;
import ru.bstu.course.gusev.bank.service.BankOfficeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmServiceImpl implements AtmService {

    public static int currentId;
    private final Map<Integer, BankAtm> atms = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public BankAtm findById(Integer id) {

        BankAtm atm = atms.get(id);

        if (atm == null) {
            System.err.println("Atm with id " + id + " is not found");
        }

        return atm;
    }

    @Override
    public List<BankAtm> fetchAll() {
        return new ArrayList<>(atms.values());
    }

    @Override
    public BankAtm create(BankAtm bankAtm) {

        if (AtmValidator.validateCreate(bankAtm)) return null;

        BankAtm atm = new BankAtm(bankAtm);

        atms.put(atm.getId(), atm);
        bankOfficeService.installAtm(atm.getBankOffice().getId(), atm);

        return atm;
    }

    @Override
    public void delete(BankAtm entity) {

    }

    @Override
    public void update(BankAtm entity) {



    }

    @Override
    public boolean depositMoney(BankAtm bankAtm, BigDecimal amount) {

        if (AtmValidator.validateDepositMoney(bankAtm, amount)) return false;

        bankAtm.setTotalMoney(bankAtm.getTotalMoney().add(amount));

        return true;
    }

    @Override
    public boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount) {

        if (AtmValidator.validateWithdrawMoney(bankAtm, amount)) return false;

        bankAtm.setTotalMoney(bankAtm.getTotalMoney().subtract(amount));

        return true;
    }

}
