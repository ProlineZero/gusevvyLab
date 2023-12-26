package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.BankAtm;

import java.math.BigDecimal;

public interface AtmService extends Crud<BankAtm> {

    boolean depositMoney(BankAtm bankAtm, BigDecimal amount);

    boolean withdrawMoney(BankAtm bankAtm, BigDecimal amount);

    boolean isAtmSuitable(BankAtm bankAtm, BigDecimal money);
}
