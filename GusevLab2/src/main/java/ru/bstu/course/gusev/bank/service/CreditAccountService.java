package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.CreditAccount;

public interface CreditAccountService extends Crud<CreditAccount> {

    boolean makeMonthlyPayment(CreditAccount creditAccount);
}
