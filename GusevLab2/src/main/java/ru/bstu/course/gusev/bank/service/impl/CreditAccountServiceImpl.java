package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.entity.CreditAccount;
import ru.bstu.course.gusev.bank.validator.CreditAccountValidator;
import ru.bstu.course.gusev.bank.service.ClientService;
import ru.bstu.course.gusev.bank.service.CreditAccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditAccountServiceImpl implements CreditAccountService {

    private final Map<Integer, CreditAccount> creditAccounts = new HashMap<>();
    private final ClientService clientService;

    public CreditAccountServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public CreditAccount findById(Integer id) {

        CreditAccount account = creditAccounts.get(id);

        if (account == null) {
            System.err.println("Credit account with id " + id + " is not found");
        }

        return account;
    }

    @Override
    public List<CreditAccount> fetchAll() {
        return new ArrayList<>(creditAccounts.values());
    }

    @Override
    public CreditAccount create(CreditAccount creditAccount) {

        if (CreditAccountValidator.validateCreate(creditAccount)) return null;

        CreditAccount newAccount = new CreditAccount(creditAccount);

        creditAccounts.put(newAccount.getId(), newAccount);
        clientService.addCreditAccount(newAccount.getClient().getId(), newAccount);

        return newAccount;
    }

    @Override
    public void delete(CreditAccount entity) {

    }

    @Override
    public void update(CreditAccount entity) {

    }

    @Override
    public boolean makeMonthlyPayment(CreditAccount creditAccount) {

        if (CreditAccountValidator.validateMakingMonthlyPayment(creditAccount)) return false;

        final BigDecimal monthlyPayment = creditAccount.getMontlyPayment();
        final BigDecimal paymentAccountBalance = creditAccount.getPaymentAccount().getBalance();

        if (paymentAccountBalance.compareTo(monthlyPayment) < 0) {

            System.err.println("Error: CreditAccount - not enough balance for monthly payment");
            return false;
        }

        creditAccount.getPaymentAccount().setBalance(paymentAccountBalance.subtract(monthlyPayment));
        creditAccount.setRemainingCreditAmount(creditAccount.getRemainingCreditAmount().subtract(monthlyPayment));

        return true;
    }
}
