package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Client extends Person {

    public static final BigDecimal MAX_MONTHLY_INCOME = new BigDecimal("10000");
    private String placeOfWork;
    private BigDecimal monthlyIncome;
    private Bank bank;
    private BigDecimal creditRating;

    public Client() {

        this.placeOfWork = "No place of work";
        this.monthlyIncome = new BigDecimal("0");
        this.bank = null;
        this.creditRating = new BigDecimal("0");    }

    public Client(Client client) {

        super(client.id, client.name, client.birthDate);

        this.placeOfWork = client.placeOfWork;
        this.monthlyIncome = client.monthlyIncome;
        this.bank = new Bank(client.bank);
        this.creditRating = client.creditRating;
    }

    public Client(String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
                  BigDecimal creditRating) {

        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    public Client(UUID id, String name, LocalDate birthDate, String placeOfWork, BigDecimal monthlyIncome, Bank bank,
                  BigDecimal creditRating) {

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.placeOfWork = placeOfWork;
        this.monthlyIncome = monthlyIncome;
        this.bank = bank;
        this.creditRating = creditRating;
    }

    @Override
    public String toString() {

        return "Client:{" +
                "\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n creditRating='" + String.format("%.2f", getCreditRating()) + "'" +
                "\n}";
    }

}
