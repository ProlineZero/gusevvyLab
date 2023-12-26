package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.*;

import java.math.BigDecimal;
import java.util.Date;

public interface CreditAccountService {
    public CreditAccount create(User user, String bankName, Date startDate,
                                Date finishDate, BigDecimal sum, BigDecimal everyMonthPay, BigDecimal interestRate,
                                Employee employee, PaymentAccount paymentAccount);

    public Boolean updatePaymentAccount(CreditAccount creditAccount, PaymentAccount paymentAccount);

    public Boolean updateInterestRate(CreditAccount creditAccount, Bank bank);
}
