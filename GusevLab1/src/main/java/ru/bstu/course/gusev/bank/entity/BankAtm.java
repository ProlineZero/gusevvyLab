package ru.bstu.course.gusev.bank.entity;

import lombok.Data;
import ru.bstu.course.gusev.bank.entity.values.BankAtmStatusValues;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BankAtm {

    private UUID id;
    private String name;
    private String address;
    private BankAtmStatusValues status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private BigDecimal totalMoney;
    private BigDecimal maintenanceCost;

    public BankAtm(BankAtm bankAtm) {

        this.id = UUID.fromString(bankAtm.id.toString());
        this.name = bankAtm.name;
        this.address = bankAtm.address;
        this.status = bankAtm.status;
        this.bank = new Bank(bankAtm.bank);
        this.bankOffice = new BankOffice(bankAtm.bankOffice);
        this.employee = new Employee(bankAtm.employee);
        this.isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        this.isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        this.totalMoney = bankAtm.totalMoney;
        this.maintenanceCost = bankAtm.maintenanceCost;
    }

    public BankAtm() {

        this.id = UUID.randomUUID();
        this.name = "No name";
        this.address = "No address";
        this.status = BankAtmStatusValues.NOT_WORKING;
        this.bank = null;
        this.bankOffice = null;
        this.employee = null;
        this.isCashWithdrawalAvailable = false;
        this.isCashDepositAvailable = false;
        this.totalMoney = new BigDecimal("0");
        this.maintenanceCost = new BigDecimal("0");
    }

    public BankAtm(String name, String address) {

        this.id = UUID.randomUUID();
        this.status = BankAtmStatusValues.NOT_WORKING;
        this.bank = null;
        this.bankOffice = null;
        this.employee = null;
        this.isCashWithdrawalAvailable = false;
        this.isCashDepositAvailable = false;
        this.totalMoney = new BigDecimal("0");
        this.maintenanceCost = new BigDecimal("0");
        this.name = name;
        this.address = address;
    }

    public BankAtm(String name, String address, BankAtmStatusValues status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
                   BigDecimal maintenanceCost) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    public BankAtm(UUID id, String name, String address, BankAtmStatusValues status, Bank bank, BankOffice bankOffice,
                   Employee employee, boolean isCashWithdrawalAvailable, boolean isCashDepositAvailable, BigDecimal totalMoney,
                   BigDecimal maintenanceCost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.status = status;
        this.bank = bank;
        this.bankOffice = bankOffice;
        this.employee = employee;
        this.isCashWithdrawalAvailable = isCashWithdrawalAvailable;
        this.isCashDepositAvailable = isCashDepositAvailable;
        this.totalMoney = totalMoney;
        this.maintenanceCost = maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm:{" +
            "\n id='" + getId() + "'" +
            ",\n name='" + getName() + "'" +
            ",\n address='" + getAddress() + "'" +
            ",\n status='" + getStatus() + "'" +
            ",\n bank='" + getBank() + "'" +
            ",\n bankOffice='" + getBankOffice() + "'" +
            ",\n employee='" + getEmployee() + "'" +
            ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
            ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
            ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
            ",\n maintenanceCost='" + String.format("%.2f", getMaintenanceCost()) + "'" +
            "\n}";
    }
}
