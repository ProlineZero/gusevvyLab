package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.PaymentAccount;
import tech.reliab.course.gusev.bank.entity.User;

import java.math.BigDecimal;

public interface PaymentAccountService {
    public PaymentAccount create(User user, Bank bank, BigDecimal sum);

    public Boolean updateSum(PaymentAccount paymentAccount, Integer add);
}
