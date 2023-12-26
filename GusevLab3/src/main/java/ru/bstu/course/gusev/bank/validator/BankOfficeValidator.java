package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.BankOffice;

import java.math.BigDecimal;

public class BankOfficeValidator  {

    public static boolean validateCreate(BankOffice bankOffice) {

        if (bankOffice == null) {
            return true;
        }

        if (bankOffice.getTotalMoney().signum() < 0) {
            System.err.println("Error: BankOffice - total money must be non-negative");
            return true;
        }

        if (bankOffice.getBank() == null) {
            System.err.println("Error: BankOffice - must have bank");
            return true;
        }

        if (bankOffice.getRentPrice().signum() < 0) {
            System.err.println("Error: BankOffice - rentPrice must be non-negative");
            return true;
        }

        return false;
    }

    public static boolean validateInstallAtm(BankOffice bankOffice) {

        if (!bankOffice.isAtmPlaceable()) {
            System.err.println("Error: BankOffice - cannot install atm");
            return true;
        }

        return false;
    }

    public static boolean validateRemoveAtm(int newAtmCountOffice) {

        if (newAtmCountOffice < 0) {
            System.err.println("Error: BankOffice - cannot remove ATM, no ATMs");
            return true;
        }

        return false;
    }

    public static boolean validateDepositMoney(BankOffice bankOffice, BigDecimal amount) {

        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot deposit money to not existing office");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot deposit money - deposit amount must be positive");
            return true;
        }

        if (!bankOffice.isCashDepositAvailable()) {
            System.err.println("Error: BankOffice - cannot deposit money - office cannot accept deposit");
            return true;
        }

        return false;
    }

    public static boolean validateWithdrawMoney(BankOffice bankOffice, BigDecimal amount) {

        if (bankOffice == null) {
            System.err.println("Error: BankOffice - cannot withdraw money from not existing office");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - withdraw amount must be positive");
            return true;
        }

        if (!bankOffice.isCashWithdrawalAvailable()) {
            System.err.println("Error: BankOffice - cannot withdraw money - office cannot give withdrawal");
            return true;
        }

        if (bankOffice.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankOffice - cannot withdraw money - office does not have enough money");
            return true;
        }

        return false;
    }
}
