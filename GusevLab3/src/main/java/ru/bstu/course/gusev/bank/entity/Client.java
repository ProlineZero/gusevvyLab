package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Client extends Person {

    private String workPlace;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;

    public Client() {

        this.workPlace = "No place of work";
        this.monthlyIncome = new BigDecimal("0");
        this.bank = null;
        this.creditRating = new BigDecimal("0");    }

    public Client(Client client) {

        super(client.id, client.name, client.birthDate);

        this.workPlace = client.workPlace;
        this.monthlyIncome = client.monthlyIncome;
        this.bank = new Bank(client.bank);
        this.creditRating = client.creditRating;
    }

    public Client(
        String name, LocalDate birthDate, String workPlace, BigDecimal monthlyIncome, Bank bank, BigDecimal creditRating
    ) {

        super(name, birthDate);

        this.name = name;
        this.birthDate = birthDate;
        this.workPlace = workPlace;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public Client(
        int id, String name, LocalDate birthDate, String workPlace, BigDecimal monthlyIncome, Bank bank,
        BigDecimal creditRating
    ) {

        super(id, name, birthDate);

        this.workPlace = workPlace;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {

        return """
            Client:
                person = '%s',
                placeOfWork = '%s',
                monthlyIncome = '%.2f',
                bank = '%s',
                creditRating = '%.2f'
            """.formatted(super.toString(), getWorkPlace(), getMonthlyIncome(), getBank().getName(), getCreditRating());
    }

}