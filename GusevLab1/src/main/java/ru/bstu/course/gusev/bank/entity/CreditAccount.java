package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
        this.paymentAccount = null;    }

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

    public CreditAccount(Client client, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
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

    public CreditAccount(UUID id, Client client, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
                         BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
                         BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {

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

        return "CreditAccount:{" +
                "\n account='" + super.toString() + "'" +
                ",\n dateStart='" + getDateStart() + "'" +
                ",\n dateEnd='" + getDateEnd() + "'" +
                ",\n monthCount='" + getMonthCount() + "'" +
                ",\n creditAmount='" + String.format("%.2f", getCreditAmount()) + "'" +
                ",\n remainingCreditAmount='" + String.format("%.2f", getRemainingCreditAmount()) + "'" +
                ",\n montlyPayment='" + String.format("%.2f", getMontlyPayment()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n paymentAccount='" + getPaymentAccount() + "'" +
                "\n}";
    }
}
