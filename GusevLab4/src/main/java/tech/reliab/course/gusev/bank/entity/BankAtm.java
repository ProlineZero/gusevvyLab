package tech.reliab.course.gusev.bank.entity;

import tech.reliab.course.gusev.bank.service.AtmService;
import tech.reliab.course.gusev.bank.utils.AtmStatus;
import tech.reliab.course.gusev.bank.utils.Utils;

import java.math.BigDecimal;


public class BankAtm {
    static private Integer idCurrent = 0;
    private Integer id;
    private String name;
    private String address;
    private AtmStatus status;
    private Bank bank;
    private BankOffice locate;
    private Employee employee;
    private Boolean giveWork;
    private Boolean getWork;
    private BigDecimal money;
    private BigDecimal maintenanceCost;

    public BankAtm(Integer id, Bank bank, BankOffice bankOffice, Employee employee, String name, AtmStatus status, Boolean giveWork, Boolean getWork,
                   BigDecimal money, BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = bankOffice.getAddress();
        this.status = status;
        this.bank = bank;
        this.locate = bankOffice;
        this.employee = employee;
        this.giveWork = giveWork;
        this.getWork = getWork;
        this.money = money;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(Bank bank, BankOffice bankOffice, Employee employee, String name, AtmStatus status, Boolean giveWork, Boolean getWork,
                   BigDecimal money, BigDecimal maintenanceCost) {
        id = idCurrent++;
        this.name = name;
        this.address = bankOffice.getAddress();
        this.status = status;
        this.bank = bank;
        this.locate = bankOffice;
        this.employee = employee;
        this.giveWork = giveWork;
        this.getWork = getWork;
        this.money = money;
        this.maintenanceCost = maintenanceCost;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Bank getBank() {
        return bank;
    }

    public AtmStatus getStatus() {
        return status;
    }

    public BankOffice getLocate() {
        return locate;
    }

    public Boolean getGetWork() {
        return getWork;
    }

    public Boolean getGiveWork() {
        return giveWork;
    }

    public Employee getEmployee() {
        return employee;
    }

    public BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    public String getAddress() {
        return address;
    }

    public void setLocate(BankOffice locate) {
        this.locate = locate;
    }

    public String statusToString() {
        switch (this.status) {
            case WORK : {
                return "работает";
            }
            case NEED_FIX: {
                return "поломан";
            }
            case WAIT_MONEY: {
                return "закончились деньги";
            }
        }
        return "Ошибка определения статуса\n";
    }
    @Override
    public String toString() {
        return "Объект: банкомат\n" + "++++++++++++++++++++++++++++++++++++++\n" +
                "Имя банкомата: " + this.name + "\n" +
                "Айди банкомата: " + this.id + "\n" +
                "Адрес: " + this.address + "\n" +
                "Статус: " + this.statusToString() + "\n" +
                "Связан с банком: " + this.bank.getName() + "\n" +
                "Закреплен за офисом: " + this.locate.getName() + "\n" +
                "Закреплен за работником: " + this.employee.getFullName() + "\n" +
                "Работает ли выдача наличных: " + Utils.logic(this.giveWork) + "\n" +
                "Работает ли внесение наличных: " + Utils.logic(this.getWork) + "\n" +
                "Количество денег в банкомате: " + this.money + "\n" +
                "Стоимость обслуживания: " + String.format("%.2f", this.maintenanceCost) + "\n" +
                "-------------------------------------------------------\n";


    }

    public void setStatus(AtmStatus status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
