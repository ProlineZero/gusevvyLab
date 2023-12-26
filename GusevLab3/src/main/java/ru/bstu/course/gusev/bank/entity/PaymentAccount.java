package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;

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

    public PaymentAccount(int id, Client client, Bank bank, BigDecimal balance) {

        super(id, client, bank);

        this.balance = balance;
    }

    @Override
    public String toString() {

        return """
            PaymentAccount:
                account = '%s',
                balance = '%.2f'
            """.formatted(super.toString(), getBalance());
    }

}
