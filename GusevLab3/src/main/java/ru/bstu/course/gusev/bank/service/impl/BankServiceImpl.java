package ru.bstu.course.gusev.bank.service.impl;

import ru.bstu.course.gusev.bank.Utils;
import ru.bstu.course.gusev.bank.entity.*;
import ru.bstu.course.gusev.bank.entity.values.Constants;
import ru.bstu.course.gusev.bank.validator.BankValidator;
import ru.bstu.course.gusev.bank.service.BankOfficeService;
import ru.bstu.course.gusev.bank.service.BankService;
import ru.bstu.course.gusev.bank.service.ClientService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;

public class BankServiceImpl implements BankService {

    public static int currentId;
    private final Map<Integer, Bank> banks = new HashMap<>();
    private final Map<Integer, List<BankOffice>> officesByBankId = new HashMap<>();
    private final Map<Integer, List<Client>> clientsByBankId = new HashMap<>();

    private BankOfficeService bankOfficeService;
    private ClientService clientService;

    @Override
    public Bank findById(Integer id) {

        Bank bank = banks.get(id);

        if (bank == null) {
            System.err.println("Bank with id " + id + " is not found");
        }

        return bank;
    }

    @Override
    public List<Bank> fetchAll() {
        return new ArrayList<>(banks.values());
    }

    @Override
    public Bank create(Bank bank) {

        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getName());

        final Random random = new Random();

        newBank.setRating((byte) random.nextInt(Constants.MAX_RATING.intValue() + 1));
        newBank.setTotalMoney(
            Utils.between(new BigDecimal("0.0"), new BigDecimal("1.0").multiply(Constants.MAX_TOTAL_MONEY))
        );

        calculateInterestRate(newBank);

        banks.put(newBank.getId(), newBank);
        officesByBankId.put(newBank.getId(), new ArrayList<>());
        clientsByBankId.put(newBank.getId(), new ArrayList<>());

        return newBank;
    }

    @Override
    public void delete(Bank entity) {

    }

    @Override
    public void update(Bank entity) {

    }

    @Override
    public void setBankOfficeService(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<BankOffice> getAllOfficesByBankId(int id) {

        Bank bank = findById(id);

        if (bank != null) {
            return officesByBankId.get(id);
        }

        return new ArrayList<>();
    }

    @Override
    public boolean addOffice(int bankId, BankOffice bankOffice) {

        Bank bank = findById(bankId);

        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            bank.setAtmCount(bank.getAtmCount() + bankOffice.getAtmCount());
            depositMoney(bankId, bankOffice.getTotalMoney());
            List<BankOffice> bankOffices = getAllOfficesByBankId(bankId);
            bankOffices.add(bankOffice);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeOffice(int bankId, BankOffice bankOffice) {

        Bank bank = findById(bankId);

        int officeIndex = officesByBankId.get(bankId).indexOf(bankOffice);

        if (bank != null && officeIndex >= 0) {

            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("Error: Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            bank.setAtmCount(bank.getAtmCount() - officesByBankId.get(bankId).get(officeIndex).getAtmCount());
            officesByBankId.get(bankId).remove(officeIndex);

            return true;
        }

        return false;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {

        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {

        if (bank != null && employee != null) {

            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.err.println("Error: Bank - cannot remove employee, no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }

        return false;
    }

    @Override
    public boolean addClient(int id, Client client) {

        Bank bank = findById(id);

        if (bank != null && client != null) {

            client.setBank(bank);
            bank.setClientCount(bank.getClientCount() + 1);

            List<Client> clients = clientsByBankId.get(id);
            clients.add(client);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeClient(Bank bank, Client client) {

        if (bank != null && client != null) {

            int newClientCount = bank.getClientCount() - 1;

            if (newClientCount < 0) {
                System.err.println("Error: Bank - cannot remove client, no clients");
                return false;
            }

            bank.setClientCount(newClientCount);

            return true;
        }

        return false;
    }

    @Override
    public BigDecimal calculateInterestRate(Bank bank) {

        if (bank != null) {

            final BigDecimal rating = BigDecimal.valueOf(bank.getRating());

            final BigDecimal centralBankInterestRate = Utils.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(Bank.MAX_INTEREST_RATE);
            final BigDecimal maxBankInterestRateMargin = Bank.MAX_INTEREST_RATE.subtract(centralBankInterestRate);
            final BigDecimal bankInterestRateMargin = (Utils.between(new BigDecimal("0.0"), new BigDecimal("1.0"))
                    .multiply(maxBankInterestRateMargin))
                    .multiply((new BigDecimal("110").subtract(rating).divide(new BigDecimal("100"), MathContext.DECIMAL128)));
            final BigDecimal interestRate = centralBankInterestRate.add(bankInterestRateMargin);

            bank.setInterestRate(interestRate.multiply(Utils.between(new BigDecimal(2), new BigDecimal(10)), MathContext.DECIMAL128));
            return interestRate;
        }
        return new BigDecimal("0");
    }

    @Override
    public boolean depositMoney(int id, BigDecimal amount) {

        Bank bank = findById(id);

        if (BankValidator.validateDepositMoney(amount, bank)) return false;

        bank.setTotalMoney(bank.getTotalMoney().add(amount));
        return true;
    }

    @Override
    public boolean withdrawMoney(int id, BigDecimal amount) {

        Bank bank = findById(id);

        if (BankValidator.validateWithdrawMoney(amount, bank)) return false;

        bank.setTotalMoney(bank.getTotalMoney().subtract(amount));

        return true;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) throws Exception {
        if ((account != null) && (bank != null) && (employee != null)) {

            BigDecimal sum = account.getCreditAmount();

            if (bank.getTotalMoney().compareTo(sum) >= 0) {
                if (employee.isCreditAvailable()) {
                    BigDecimal sumMonthPay = sum
                            .multiply((bank.getInterestRate().divide(new BigDecimal(100), MathContext.DECIMAL128).add(new BigDecimal(1))))
                            .divide(new BigDecimal(account.getMonthCount()), MathContext.DECIMAL128);

                    if (account.getClient().getMonthlyIncome().compareTo(sumMonthPay) >= 0) {
                        if (account.getClient().getCreditRating().compareTo(new BigDecimal(5000)) < 0
                                && bank.getRating() > 50) {
                            return false;
                        }
                        account.setEmployee(employee);
                        account.setMontlyPayment(sumMonthPay);
                        account.setBank(bank);
                        account.setEmployee(employee);
                        account.setInterestRate(bank.getInterestRate());

                        LocalDate dateEnd = account.getDateStart();
                        dateEnd = dateEnd.plusMonths(account.getMonthCount());
                        account.setDateEnd(dateEnd);
                        return true;
                    } else {
                        throw new Exception("Ошибка при взятии кредита");
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void printData(final Integer bankId) {

        Bank bank = findById(bankId);

        if (bank == null) {
            return;
        }

        System.out.println("=====================");
        System.out.println(bank);

        List<BankOffice> offices = officesByBankId.get(bankId);

        if (offices != null) {

            System.out.println("Offices:");
            offices.forEach((BankOffice office) -> bankOfficeService.printData(office.getId()));
        }

        List<Client> clients = clientsByBankId.get(bankId);

        if (clients != null) {

            System.out.println("Clients:");
            clients.forEach((Client client) -> clientService.printClientData(client.getId(), false));
        }

        System.out.println("=====================");
    }

    @Override
    public List<Bank> getBanksSuitable(BigDecimal sum, int countMonth) throws Exception {

        List<Bank> banksSuitable = new ArrayList<>();

        for (Bank bank : banks.values()) {
            if (isBankSuitable(bank, sum)) {
                banksSuitable.add(bank);
            }
        }

        if (banksSuitable.isEmpty()) {
            throw new Exception("Error: can't give credit");
        }

        return banksSuitable;
    }

    @Override
    public boolean isBankSuitable(Bank bank, BigDecimal money) throws Exception {
        List<BankOffice> bankOfficeSuitable = getBankOfficeSuitableInBank(bank, money);
        return !bankOfficeSuitable.isEmpty();
    }

    @Override
    public List<BankOffice> getBankOfficeSuitableInBank(Bank bank, BigDecimal money) throws Exception {
        List<BankOffice> bankOfficesByBank = getAllOfficesByBankId(bank.getId());
        List<BankOffice> suitableBankOffice = new ArrayList<>();

        for (BankOffice bankOffice : bankOfficesByBank) {
            if (bankOfficeService.isSuitableBankOffice(bankOffice, money)) {
                suitableBankOffice.add(bankOffice);
            }
        }

        return suitableBankOffice;
    }
}
