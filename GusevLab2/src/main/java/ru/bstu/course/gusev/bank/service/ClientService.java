package ru.bstu.course.gusev.bank.service;

import ru.bstu.course.gusev.bank.entity.Client;
import ru.bstu.course.gusev.bank.entity.CreditAccount;
import ru.bstu.course.gusev.bank.entity.PaymentAccount;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService extends Crud<Client> {

    boolean addPaymentAccount(int id, PaymentAccount account);

    boolean addCreditAccount(int id, CreditAccount account);

    List<PaymentAccount> getAllPaymentAccountsByClientId(int id);

    List<CreditAccount> getAllCreditAccountsByClientId(int id);

    BigDecimal calculateCreditRating(Client client);

    void printClientData(int id, boolean withAccounts);

}
