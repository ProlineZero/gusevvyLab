package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.PaymentAccount;
import ru.bstu.course.gusev.bank.validator.PaymentAccountValidator;
import ru.bstu.course.gusev.bank.service.ClientService;
import ru.bstu.course.gusev.bank.service.PaymentAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final Map<Integer, PaymentAccount> paymentAccounts = new HashMap<>();
    private final ClientService clientService;

    public PaymentAccountServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public PaymentAccount findById(Integer id) {

        PaymentAccount account = paymentAccounts.get(id);

        if (account == null) {
            System.err.println("Payment account with id " + id + " is not found");
        }

        return account;
    }

    @Override
    public List<PaymentAccount> fetchAll() {
        return new ArrayList<>(paymentAccounts.values());
    }

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {

        if (PaymentAccountValidator.validateCreate(paymentAccount)) return null;

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);

        paymentAccounts.put(newAccount.getId(), newAccount);
        clientService.addPaymentAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

    @Override
    public void delete(PaymentAccount entity) {

    }

    @Override
    public void update(PaymentAccount entity) {

    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, BigDecimal amount) {

        if (PaymentAccountValidator.validateDepositMoney(paymentAccount, amount)) return false;

        paymentAccount.setBalance(paymentAccount.getBalance().add(amount));

        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, BigDecimal amount) {

        if (PaymentAccountValidator.validateWithdrawMoney(paymentAccount, amount)) return false;

        paymentAccount.setBalance(paymentAccount.getBalance().subtract(amount));

        return true;
    }

    @Override
    public BigDecimal getTotalMoney(int id) throws Exception {
        PaymentAccount paymentAccount = findById(id);
        return paymentAccount.getBalance();
    }

}
