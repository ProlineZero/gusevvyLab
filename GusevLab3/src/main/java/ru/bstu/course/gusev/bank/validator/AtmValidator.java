package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.BankAtm;

import java.math.BigDecimal;

public class AtmValidator {

    public static boolean validateDepositMoney(BankAtm bankAtm, BigDecimal amount) {

        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot deposit money - non existing ATM");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot deposit money - amount is not positive");
            return true;
        }

        if (!bankAtm.isCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot deposit money - deposit is not allowed");
            return true;
        }

        return false;
    }

    public static boolean validateWithdrawMoney(BankAtm bankAtm, BigDecimal amount) {

        if (bankAtm == null) {
            System.err.println("Error: BankAtm cannot withdraw money - non existing ATM");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: BankAtm cannot withdraw money - amount is not positive");
            return true;
        }

        if (!bankAtm.isCashDepositAvailable()) {
            System.err.println("Error: BankAtm cannot withdraw money - deposit is not allowed");
            return true;
        }

        if (bankAtm.getTotalMoney().compareTo(amount) < 0) {
            System.err.println("Error: BankAtm cannot withdraw money - ATM does not have enough money");
            return true;
        }

        return false;
    }

    public static boolean validateCreate(BankAtm bankAtm) {

        if (bankAtm == null) {
            return true;
        }

        if (bankAtm.getTotalMoney().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - totalMoney must be non-negative");
            return true;
        }

        if (bankAtm.getMaintenanceCost().signum() < 0) {
            System.err.println("Error: cannot create BankAtm - maintenanceCost must be non-negative");
            return true;
        }

        if (bankAtm.getBankOffice() == null) {
            System.err.println("Error: cannot create BankAtm - bankOffice cannot be null");
            return true;
        }

        return false;
    }
}
