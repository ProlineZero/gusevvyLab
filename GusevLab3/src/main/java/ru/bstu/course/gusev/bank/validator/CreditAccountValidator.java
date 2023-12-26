package ru.bstu.course.gusev.bank.validator;

import ru.bstu.course.gusev.bank.entity.CreditAccount;

public class CreditAccountValidator {

    public static boolean validateCreate(CreditAccount creditAccount) {

        if (creditAccount == null) {
            return true;
        }

        if (creditAccount.getMonthCount() < 1) {
            System.err.println("Error: CreditAccount - monthCount must be at least 1");
            return true;
        }

        if (creditAccount.getCreditAmount().signum() <= 0) {
            System.err.println("Error: CreditAccount - creditAmount must be positive");
            return true;
        }

        if (creditAccount.getBank() == null) {
            System.err.println("Error: CreditAccount - no bank");
            return true;
        }

        return false;
    }

    public static boolean validateMakingMonthlyPayment(CreditAccount creditAccount) {

        if (creditAccount == null || creditAccount.getPaymentAccount() == null) {
            System.err.println("Error: CreditAccount - no account to take money from");
            return true;
        }

        return false;
    }
}
