package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Account {

    protected UUID id;
    protected Client client;
    protected Bank bank;

    public Account() {

        this.id = UUID.randomUUID();
        this.client = null;
        this.bank = null;    }

    public Account(Client client, Bank bank) {

        this.id = UUID.randomUUID();
        this.client = null;
        this.bank = bank;
    }

    public Account(UUID id, Client client, Bank bank) {

        this.id = id;
        this.client = client;
        this.bank = bank;
    }

    public Account(Account account) {

        this.id = UUID.fromString(account.id.toString());
        this.client = new Client(account.client);
        this.bank = new Bank(account.bank);
    }

    @Override
    public String toString() {

        return "Account:{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getClient() + "'" +
                ",\n bank='" + getBank() + "'" +
                "\n}";
    }

}
