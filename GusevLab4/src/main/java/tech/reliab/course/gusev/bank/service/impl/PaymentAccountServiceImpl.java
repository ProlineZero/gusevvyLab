package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.entity.Bank;
import tech.reliab.course.gusev.bank.entity.PaymentAccount;
import tech.reliab.course.gusev.bank.entity.User;
import tech.reliab.course.gusev.bank.service.PaymentAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    Map<Integer, List<PaymentAccount>> tableAccountByBankId = new HashMap<>();
    @Override
    public PaymentAccount create(User user, Bank bank, BigDecimal sum) {
        PaymentAccount newPaymentAccount = new PaymentAccount(user,
                bank, sum);
        if (tableAccountByBankId.get(bank.getId()) == null) {
            tableAccountByBankId.put(bank.getId(), new ArrayList<PaymentAccount>());
        }
        tableAccountByBankId.get(bank.getId()).add(newPaymentAccount);
        return newPaymentAccount;
    }

    @Override
    public Boolean updateSum(PaymentAccount paymentAccount, Integer add) {
        if (paymentAccount != null && add != null) {
            BigDecimal var = new BigDecimal(-add);
            if (paymentAccount.getSum().compareTo(var.negate()) > 0) {
                paymentAccount.setSum(paymentAccount.getSum().add(var));
                return true;
            }
        }
        return false;
    }
}
