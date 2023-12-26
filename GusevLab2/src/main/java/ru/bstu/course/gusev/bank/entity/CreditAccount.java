package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditAccount extends Account {

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int monthCount;
    private BigDecimal creditAmount;
    private BigDecimal remainingCreditAmount;
    private BigDecimal montlyPayment;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount() {

        super();

        this.dateStart = null;
        this.dateEnd = null;
        this.monthCount = 0;
        this.creditAmount = new BigDecimal("0");
        this.remainingCreditAmount = new BigDecimal("0");
        this.montlyPayment = new BigDecimal("0");
        this.interestRate = new BigDecimal("0");
        this.employee = null;
        this.paymentAccount = null;
    }

    public CreditAccount(CreditAccount creditAccount) {

        super(creditAccount.id, creditAccount.client, creditAccount.bank);

        this.dateStart = creditAccount.dateStart;
        this.dateEnd = creditAccount.dateEnd;
        this.monthCount = creditAccount.monthCount;
        this.creditAmount = creditAccount.creditAmount;
        this.remainingCreditAmount = creditAccount.remainingCreditAmount;
        this.montlyPayment = creditAccount.montlyPayment;
        this.interestRate = creditAccount.interestRate;
        this.employee = new Employee(creditAccount.employee);
        this.paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public CreditAccount(
        Client client, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
        BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
        BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount
    ) {

        super(client, bank);

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(
        int id, Client client, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
        BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
        BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount
    ) {
        super(id, client, bank);

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {

        return """
            CreditAccount:
                account = '%s',
                dateStart = '%s',
                dateEnd = '%s',
                monthCount = '%s',
                creditAmount = '%.2f',
                remainingCreditAmount = '%.2f',
                montlyPayment = '%.2f',
                interestRate = '%.2f',
                employee = '%s',
                paymentAccount = '%s'
            """.formatted(
                    super.toString(), getDateStart(), getDateEnd(), getMonthCount(), getCreditAmount(),
                    getRemainingCreditAmount(), getMontlyPayment(), getInterestRate(), getEmployee(),
                    getPaymentAccount()
                );
    }
}
