package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentAccount extends Account {

    private BigDecimal balance;

    public PaymentAccount() {

        super();

        this.balance = new BigDecimal("0");
    }

    public PaymentAccount(PaymentAccount paymentAccount) {

        super(paymentAccount.id, paymentAccount.client, paymentAccount.bank);

        this.balance = paymentAccount.balance;
    }

    public PaymentAccount(Client client, Bank bank, BigDecimal balance) {

        super(client, bank);

        this.balance = balance;
    }

    public PaymentAccount(UUID id, Client client, Bank bank, BigDecimal balance) {

        super(id, client, bank);

        this.balance = balance;
    }

    @Override
    public String toString() {

        return "PaymentAccount:{" +
            "\n account='" + super.toString() + "'" +
            ",\n balance='" + String.format("%.2f", getBalance()) + "'" +
            "\n}";
    }

}
