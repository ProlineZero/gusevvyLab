package tech.reliab.course.gusev.bank.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private static Integer idCurrent = 0;
    private Integer id;
    private String fullName;
    private Date birthday;
    private String work;
    private BigDecimal salary;
    private BigDecimal rating;
    private ArrayList<Bank> banks;
    private ArrayList<CreditAccount> creditAccounts;
    private ArrayList<PaymentAccount> paymentAccounts;

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public ArrayList<CreditAccount> getCreditAccounts() {

        return creditAccounts;
    }

    public List<CreditAccount> getCreditAccountsFilterOfBank(Bank bank) {
        List<CreditAccount> res = new ArrayList<CreditAccount>();
        for (CreditAccount el : creditAccounts) {
            if (Objects.equals(el.getNameBank(), bank.getName())) {
                res.add(el);
            }
        }
        return res;
    }

    public ArrayList<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public List<PaymentAccount> getPaymentAccountsFilterOfBank(Bank bank) {
        List<PaymentAccount> res = new ArrayList<PaymentAccount>();
        for (PaymentAccount el : paymentAccounts) {
            if (el.getBank() == bank) {
                res.add(el);
            }
        }
        return res;
    }

    public Date getBirthday() {
        return birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public String getWork() {
        return work;
    }

    public User(Integer id, String fullName, Date birthday, String work, BigDecimal salary, BigDecimal rating) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.work = work;
        this.salary = salary;
        this.rating = rating;
        this.banks = new ArrayList<Bank>();
        this.creditAccounts = new ArrayList<CreditAccount>();
        this.paymentAccounts = new ArrayList<PaymentAccount>();
    }

    public User(String fullName, Date birthday, String work, BigDecimal salary, BigDecimal rating) {
        id = idCurrent++;
        this.fullName = fullName;
        this.birthday = birthday;
        this.work = work;
        this.salary = salary;
        this.rating = rating;
        this.banks = new ArrayList<Bank>();
        this.creditAccounts = new ArrayList<CreditAccount>();
        this.paymentAccounts = new ArrayList<PaymentAccount>();
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        String res = "Объект: пользователь\n" + "++++++++++++++++++++++++++++++++++++++\n" +
                "Айди пользователя: " + id + "\n" +
                "Имя пользователя: " + fullName + "\n" +
                "День рождения: " + this.birthday.getDate() + "."  + (this.birthday.getMonth() + 1) + "."+ (this.birthday.getYear() + 1900) + "\n" +
                "Место работы: " + work + "\n" +
                "Зарплата: " + String.format("%.2f", salary) + "\n" +
                "Кредитный рейтинг: " + String.format("%.2f", rating) + "\n";
        res += "Пользуется банками: \n";
        for (int i = 0; i < banks.size(); i++) {
            res += "Название: " + banks.get(i).getName() + "\n";
        }
        if (banks.size() == 0)
            res += "Пользователь не пользуется банками\n";
        res += "Кредитные счета пользователя: \n";
        for (int i = 0; i < creditAccounts.size(); i++) {
            res += "Айди: " + creditAccounts.get(i).getId() + "\n";
        }
        if (creditAccounts.size() == 0)
            res += "Пользователь не имеет кредитных счетов\n";
        res += "Имеет банковские счета: \n";
        for (int i = 0; i < paymentAccounts.size(); i++) {
            res += "Айди: " + paymentAccounts.get(i).getId() + "\n";
        }
        if (paymentAccounts.size() == 0)
            res += "Пользователь не имеет денежных счетов в банках\n";
        return res + "------------------------------------------------\n";

    }
}
