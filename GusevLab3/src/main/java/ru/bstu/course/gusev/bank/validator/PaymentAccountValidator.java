package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.PaymentAccount;

import java.math.BigDecimal;

public class PaymentAccountValidator {
    public static boolean validateCreate(PaymentAccount paymentAccount) {

        if (paymentAccount == null) {
            return true;
        }

        if (paymentAccount.getBalance().signum() < 0) {
            System.err.println("Error: PaymentAccount - payment account balance must be non-negative");
            return true;
        }

        return false;
    }

    public static boolean validateDepositMoney(PaymentAccount paymentAccount, BigDecimal amount) {

        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: PaymentAccount - deposit amount must be positive");
            return true;
        }

        return false;
    }

    public static boolean validateWithdrawMoney(PaymentAccount paymentAccount, BigDecimal amount) {

        if (paymentAccount == null) {
            System.err.println("Error: PaymentAccount - non existing payment account");
            return true;
        }

        if (amount.signum() <= 0) {
            System.err.println("Error: PaymentAccount - withdrawal amount must be positive");
            return true;
        }

        if (paymentAccount.getBalance().compareTo(amount) < 0) {
            System.err.println("Error:PaymentAccount - not enough money");
            return true;
        }

        return false;
    }
}
