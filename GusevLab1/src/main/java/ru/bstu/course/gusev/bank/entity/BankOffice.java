package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BankOffice {

    private UUID id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal rentPrice;

    public BankOffice(String name, String address) {

        this.id = UUID.randomUUID();
        this.bank = null;
        this.isWorking = false;
        this.isAtmPlaceable = false;
        this.atmCount = 0;
        this.isCreditAvailable = false;
        this.isCashWithdrawalAvailable = false;
        this.isCashDepositAvailable = false;
        this.totalMoney = new BigDecimal("0");
        this.rentPrice = new BigDecimal("0");        this.name = name;
        this.address = address;
    }

    public BankOffice(UUID id, String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      BigDecimal totalMoney, BigDecimal rentPrice) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(String name, String address, Bank bank, boolean isWorking, boolean isAtmPlaceable,
                      int atmCount, boolean isCreditAvailable, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable,
                      BigDecimal totalMoney, BigDecimal rentPrice) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.isWorking = isWorking;
        this.isAtmPlaceable = isAtmPlaceable;
        this.atmCount = atmCount;
        this.isCreditAvailable = isCreditAvailable;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.rentPrice = rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {

        this.id = UUID.fromString(bankOffice.id.toString());
        this.name = bankOffice.name;
        this.address = bankOffice.address;
        this.bank = new Bank(bankOffice.bank);
        this.isWorking = bankOffice.isWorking;
        this.isAtmPlaceable = bankOffice.isAtmPlaceable;
        this.atmCount = bankOffice.atmCount;
        this.isCreditAvailable = bankOffice.isCreditAvailable;
        this.isCashWithdrawalAvailable = bankOffice.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankOffice.isCashDepositAvailable;
        this.totalMoney = bankOffice.totalMoney;
        this.rentPrice = bankOffice.rentPrice;
    }

    @Override
    public String toString() {

        return "BankOffice:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n bank='" + getBank() + "'" +
                ",\n isWorking='" + isWorking + "'" +
                ",\n isAtmPlaceable='" + isAtmPlaceable + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
                "\n}";
    }
}
