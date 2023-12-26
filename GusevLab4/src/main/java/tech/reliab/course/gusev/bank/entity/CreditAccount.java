package tech.reliab.course.gusev.bank.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CreditAccount {
    static private Integer idCurrent = 0;
    private Integer id;
    private User user;
    private String nameBank;
    private Date dateStart;
    private Date dateFinish;
    private Integer months;
    private BigDecimal sum;
    private BigDecimal everyMonthPay;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public Employee getEmployee() {
        return employee;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public BigDecimal getEveryMonthPay() {
        return everyMonthPay;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public Integer getMonths() {
        return months;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getNameBank() {
        return nameBank;
    }

    public User getUser() {
        return user;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public CreditAccount(Integer id, User user, String nameBank, Date dateStart, Date dateFinish,
                         BigDecimal sum, BigDecimal everyMonthPay, BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.id = id;
        this.user = user;
        this.nameBank = nameBank;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        long elapsedms = dateFinish.getTime() - dateStart.getTime();
        long diff = TimeUnit.DAYS.convert(elapsedms, TimeUnit.MILLISECONDS) / 30;
        this.months = (int)diff;
        this.sum = sum;
        this.everyMonthPay = everyMonthPay;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;

    }

    public CreditAccount(User user, String nameBank, Date dateStart, Date dateFinish,
                         BigDecimal sum, BigDecimal everyMonthPay, BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.id = idCurrent++;
        this.user = user;
        this.nameBank = nameBank;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        long elapsedms = dateFinish.getTime() - dateStart.getTime();
        long diff = TimeUnit.DAYS.convert(elapsedms, TimeUnit.MILLISECONDS) / 30;
        this.months = (int)diff;
        this.sum = sum;
        this.everyMonthPay = everyMonthPay;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;

    }

    public String toString() {
        return "Объект: кредитный счет\n" + "++++++++++++++++++++++++++++++++++++++\n" +
                "Айди кредитного счета: " + this.id + "\n" +
                "Владелец счета: " + this.user.getFullName() + "\n" +
                "Банк счета: " + this.nameBank + "\n" +
                "Дата открытия счета: " +  this.dateStart.getDate() + "."  + (this.dateStart.getMonth() + 1) + "."+ (this.dateStart.getYear() + 1900) +"\n" +
                "Дата закрытия счета: " + this.dateFinish.getDate() + "."  + (this.dateFinish.getMonth() + 1) + "."+ (this.dateFinish.getYear() + 1900) +"\n" +
                "Число месяцев на которое взят кредит: " + this.months + "\n" +
                "Сумма кредита: " + String.format("%.2f",this.sum) + "\n" +
                "Ежемесячный платеж: " + String.format("%.2f", this.everyMonthPay) + "\n" +
                "Процентная ставка: " + String.format("%.2f", this.interestRate) + "\n" +
                "Работник выдавший кредит: " + employee.getFullName() + "\n" +
                "Айди счета пользователя для оплаты: " + paymentAccount.getId() + "\n" +
                "-----------------------------------------------------------------\n";

    }

    public Integer getId() {
        return id;
    }
}
