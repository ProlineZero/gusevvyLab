package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Bank {

    private UUID id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int clientCount;
    private byte rating;
    private BigDecimal totalMoney;
    private BigDecimal interestRate;

    public Bank() {

        this.id = UUID.randomUUID();
        this.name = "No name";
        this.officeCount = 0;
        this.atmCount = 0;
        this.employeeCount = 0;
        this.clientCount = 0;
        this.rating = 0;
        this.totalMoney = new BigDecimal("0");
        this.interestRate = new BigDecimal("0");
    }

    public Bank(Bank bank) {

        this.id = UUID.fromString(bank.id.toString());
        this.name = bank.name;
        this.officeCount = bank.officeCount;
        this.atmCount = bank.atmCount;
        this.employeeCount = bank.employeeCount;
        this.clientCount = bank.clientCount;
        this.rating = bank.rating;
        this.totalMoney = bank.totalMoney;
        this.interestRate = bank.interestRate;
    }

    public Bank(String name) {

        this.id = UUID.randomUUID();
        this.officeCount = 0;
        this.atmCount = 0;
        this.employeeCount = 0;
        this.clientCount = 0;
        this.rating = 0;
        this.totalMoney = new BigDecimal("0");
        this.interestRate = new BigDecimal("0");
        this.name = name;
    }

    public Bank(UUID id, String name) {

        this.officeCount = 0;
        this.atmCount = 0;
        this.employeeCount = 0;
        this.clientCount = 0;
        this.rating = 0;
        this.totalMoney = new BigDecimal("0");
        this.interestRate = new BigDecimal("0");
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {

        return "Bank:{" +
            "\n id='" + getId() + "'" +
            ",\n name='" + getName() + "'" +
            ",\n officeCount='" + getOfficeCount() + "'" +
            ",\n atmCount='" + getAtmCount() + "'" +
            ",\n employeeCount='" + getEmployeeCount() + "'" +
            ",\n clientCount='" + getClientCount() + "'" +
            ",\n rating='" + getRating() + "'" +
            ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
            ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
            "\n}";
    }

}
