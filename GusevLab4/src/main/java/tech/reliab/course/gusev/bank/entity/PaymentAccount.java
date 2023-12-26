package tech.reliab.course.gusev.bank.entity;

import java.math.BigDecimal;

public class PaymentAccount {
    private static Integer idCurrent = 0;
    private final Integer id;
    private final User user;
    private final Bank bank;
    private BigDecimal sum;

    public Bank getBank() {
        return bank;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public PaymentAccount(Integer id, User user, Bank bank, BigDecimal sum) {
        this.id = id;
        this.user = user;
        this.bank = bank;
        this.sum = sum;
    }
    public PaymentAccount(User user, Bank bank, BigDecimal sum) {
        id = idCurrent++;
        this.user = user;
        this.bank = bank;
        this.sum = sum;
    }

    public Integer getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Объект: денежный счет\n" + "++++++++++++++++++++++++++++++++++++++\n" +
                "Айди счета: " + id + "\n" +
                "Владелец: " + user.getFullName() + "\n" +
                "Банк: " + bank.getName() + "\n" +
                "Сумма на счете: " + String.format("%.2f", sum) + "\n" +
                "----------------------------------\n";
    }
}


