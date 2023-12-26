package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.*;

import java.math.BigDecimal;

public class BankValidator  {
    public static boolean validateDepositMoney(BigDecimal amount, Bank bank) {

        if (bank == null) {

            System.err.println("Error: Bank - cannot deposit money to uninitialized bank");
            return true;
        }

        if (amount.signum() <= 0) {

            System.err.println("Error: Bank - cannot deposit money - deposit amount must be positive");
            return true;
        }
        return false;
    }

    public static boolean validateWithdrawMoney(BigDecimal amount, Bank bank) {

        if (bank == null) {

            System.err.println("Error: Bank - cannot withdraw money, bank is null");
            return true;
        }

        if (amount.signum() <= 0) {

            System.err.println("Error: Bank - cannot withdraw money - withdraw amount must be positive");
            return true;
        }

        if (bank.getTotalMoney().compareTo(amount) < 0) {

            System.err.println("Error: Bank - cannot withdraw money - bank does not have enough money");
            return true;
        }

        return false;
    }
}
