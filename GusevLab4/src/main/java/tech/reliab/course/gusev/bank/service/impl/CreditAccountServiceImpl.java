package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.Main;
import tech.reliab.course.gusev.bank.entity.*;
import tech.reliab.course.gusev.bank.service.CreditAccountService;

import java.math.BigDecimal;
import java.util.Date;

public class CreditAccountServiceImpl implements CreditAccountService {
    @Override
    public CreditAccount create(User user, String bankName, Date startDate,
                                Date finishDate, BigDecimal sum, BigDecimal everyMonthPay, BigDecimal interestRate, Employee
                                employee, PaymentAccount paymentAccount) {
        CreditAccount newCreditAccount = new CreditAccount(user, bankName, startDate,
                finishDate, sum, everyMonthPay,
                interestRate, employee, paymentAccount);
        return newCreditAccount;
    }

    @Override
    public Boolean updatePaymentAccount(CreditAccount creditAccount, PaymentAccount paymentAccount) {
        if (creditAccount != null && paymentAccount != null) {
            creditAccount.setPaymentAccount(paymentAccount);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateInterestRate(CreditAccount creditAccount, Bank bank) {
        if (creditAccount != null && bank != null) {
            creditAccount.setInterestRate(BigDecimal.valueOf(bank.getInterestRate().doubleValue() * Math.random()));
            return true;
        }
        return false;
    }
}
