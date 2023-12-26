package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.entity.*;
import tech.reliab.course.gusev.bank.service.BankOfficeService;
import tech.reliab.course.gusev.bank.service.BankService;
import tech.reliab.course.gusev.bank.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankServiceImpl implements BankService {

    Map<Integer, Bank> mapBanks = new HashMap<Integer, Bank>();
    @Override
    public List<User> getLstUsers(Bank bank) {
        return bank.getLstUsers();
    }

    @Override
    public List<BankOffice> getLstOffices(Bank bank) {
        return new ArrayList<BankOffice>(bank.getLstOffices());
    }

    @Override
    public Bank create(String name) {
        Bank varBank = new Bank(name);
        mapBanks.put(varBank.getId(), varBank);
        return varBank;
    }

    @Override
    public Bank getBank(Integer id) {
        return mapBanks.get(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<Bank>(mapBanks.values());
    }

    @Override
    public Boolean addAtm(Bank bank, BankAtm bankAtm) {
        if (bank != null && bankAtm != null) {
            bank.setNumberAtms(bank.getNumberAtms() + 1);
            return true;
        }
        return false;
    }

    @Override
    public Boolean addClient(Bank bank, User user) {
        if (bank != null && user != null) {
            Boolean res = bank.getLstUsers().add(user);
            if (res) {
                bank.setNumberClients(bank.getNumberClients() + 1);
            }
            return res;
        }
        return false;
    }

    @Override
    public Boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            bank.setNumberEmployees(bank.getNumberEmployees() + 1);
            return true;
        }
        return false;
    }

    @Override
    public Boolean addOffice(Bank bank, BankOffice bankOffice) {
        if (bank != null && bankOffice != null) {
            bank.setNumberOffices(bank.getNumberOffices() + 1);
            bank.getLstOffices().add(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAtm(Bank bank, BankAtm bankAtm) {
        if (bank != null && bankAtm != null) {
            if (bank.getNumberAtms() > 0) {
                bank.setNumberAtms(bank.getNumberAtms() - 1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean deleteClient(Bank bank, User user) {
        if (bank != null && user != null) {
            Boolean res = bank.getLstUsers().remove(user);
            if (res) {
                bank.setNumberClients(bank.getNumberClients() - 1);
            }
            return res;
        }
        return false;
    }

    @Override
    public Boolean deleteEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            if (bank.getNumberEmployees() > 0) {
                bank.setNumberEmployees(bank.getNumberEmployees() - 1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean deleteOffice(Bank bank, BankOffice bankOffice) {
        if (bank != null && bankOffice != null) {
            if (bank.getNumberOffices() > 0) {
                bank.setNumberOffices(bank.getNumberOffices() - 1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean putMoney(Bank bank, Integer sum) {
        if (bank != null && sum != null) {
            bank.setMoney(BigDecimal.valueOf(bank.getMoney().doubleValue() + sum));
            return true;
        }
        return false;
    }
}
