package ru.bstu.course.gusev.bank;

import ru.bstu.course.gusev.bank.entity.*;
import ru.bstu.course.gusev.bank.entity.values.BankAtmStatus;
import ru.bstu.course.gusev.bank.service.*;
import ru.bstu.course.gusev.bank.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        BankService bankService = new BankServiceImpl();
        ClientService clientService = new ClientServiceImpl(bankService);
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        bankService.setBankOfficeService(bankOfficeService);
        bankService.setClientService(clientService);

        bankService.setBankOfficeService(bankOfficeService);
        EmployeeService employeeService = new EmployeeServiceImpl(bankOfficeService);
        AtmService atmService = new AtmServiceImpl(bankOfficeService);
        bankService.setClientService(clientService);
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(clientService);
        CreditAccountService creditAccountService = new CreditAccountServiceImpl(clientService);

        createBanks(bankService);

        List<Bank> banks = bankService.fetchAll();

        createBankOffices(banks, bankOfficeService);

        List<BankOffice> offices = bankOfficeService.fetchAll();
        createOfficeEmployees(offices, employeeService, random);

        createOfficesAtms(offices, atmService, bankOfficeService, random);

        createBankClients(banks, clientService, random);

        List<Client> clients = clientService.fetchAll();
        createClientPaymentAccounts(clients, paymentAccountService, random);

        createClientCreditAccounts(
            clients, bankService, random, bankOfficeService, clientService, creditAccountService
        );

        handleUserAction(scanner, bankService, clientService);

        scanner.close();
    }

    private static void handleUserAction(Scanner scanner, BankService bankService, ClientService clientService) {

        while (true) {

            System.out.println("\nPick an action: ");
            System.out.println("1 - check bank data by bank id");
            System.out.println("2 - check client data by client id");
            System.out.println("3 - quit program");

            String action = scanner.nextLine();

            switch (action) {

                case "1":
                    handleCheckBankData(bankService, scanner);
                    break;

                case "2":
                    handleCheckClientData(clientService, scanner);
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Error: unknown action. Please, try again");
                    break;
            }
        }
    }

    private static void createBanks(BankService bankService) {

        bankService.create(new Bank("Sbebra Bank"));
        bankService.create(new Bank("Tinkonn"));
        bankService.create(new Bank("BTV"));
        bankService.create(new Bank("Beta bank"));
        bankService.create(new Bank("Dimalegenda bank"));
    }

    private static void createBankOffices(List<Bank> banks, BankOfficeService bankOfficeService) {

        for (Bank bank : banks) {
            for (int i = 1; i <= 3; i++) {
                bankOfficeService.create(
                    new BankOffice(
                        "Office №" + i + " of " + bank.getName(), "Esenina str. " + i,
                        bank, true, true, 0, true, true,
                        true, new BigDecimal("20000"), new BigDecimal(100 * i)
                    )
                );
            }
        }
    }

    private static void createOfficeEmployees(List<BankOffice> offices, EmployeeService employeeService, Random random) {

        for (BankOffice office : offices) {
            for (int i = 1; i <= 5; i++) {
                employeeService.create(
                    new Employee(
                        Utils.getNames()[random.nextInt(Utils.getNames().length)],
                        LocalDate.of(random.nextInt(1990, 2003), random.nextInt(1, 13), random.nextInt(1, 29)),
                        Utils.getRandomEmployee(), office.getBank(), true, office, true, new BigDecimal("300")
                    )
                );
            }
        }
    }

    private static void createOfficesAtms(List<BankOffice> offices, AtmService atmService, BankOfficeService bankOfficeService, Random random) {

        for (BankOffice office : offices) {
            for (int i = 1; i <= 3; i++) {
                atmService.create(
                    new BankAtm(
                        "Atm " + i, office.getAddress(), BankAtmStatus.WORKING, office.getBank(), office,
                        bankOfficeService.getAllEmployeesByOfficeId(office.getId()).get(random.nextInt(bankOfficeService.getAllEmployeesByOfficeId(office.getId()).size())),
                        true, true, new BigDecimal("0"), BigDecimal.valueOf(random.nextDouble() * 25)
                    )
                );
            }
        }
    }

    private static void createBankClients(List<Bank> banks, ClientService clientService, Random random) {

        for (Bank bank : banks) {
            for (int i = 1; i <= 5; i++) {
                clientService.create(
                    new Client(
                        Utils.getNames()[(random.nextInt(Utils.getNames().length))],
                        LocalDate.of(random.nextInt(1990, 2003), random.nextInt(1, 13), random.nextInt(1, 29)),
                        Utils.getWorkPlaces()[random.nextInt(Utils.getWorkPlaces().length)],
                        BigDecimal.valueOf(random.nextDouble() * 10000), bank, new BigDecimal(random.nextInt(10000))
                    )
                );
            }
        }
    }

    private static void createClientPaymentAccounts(List<Client> clients, PaymentAccountService paymentAccountService, Random random) {

        for (Client client : clients) {
            for (int i = 1; i <= 2; i++) {
                paymentAccountService.create(
                    new PaymentAccount(client, client.getBank(), BigDecimal.valueOf(random.nextDouble() * 10000))
                );
            }
        }
    }

    private static void createClientCreditAccounts(List<Client> clients, BankService bankService, Random random, BankOfficeService bankOfficeService, ClientService clientService, CreditAccountService creditAccountService) {

        for (Client client : clients) {
            for (int i = 1; i <= 2; i++) {

                List<BankOffice> bankOffices = bankService.getAllOfficesByBankId(client.getBank().getId());

                BankOffice randomOffice = bankOffices.get(random.nextInt(bankOffices.size()));

                List<Employee> officeEmployees = bankOfficeService.getAllEmployeesByOfficeId(randomOffice.getId());

                Employee randomEmployee = officeEmployees.get(random.nextInt(officeEmployees.size()));

                CreditAccount creditAccount = new CreditAccount(
                    client, client.getBank(), LocalDate.of(2023, 10, 1),
                    LocalDate.of(2026, 10, 1), 36,
                    new BigDecimal("2600"), new BigDecimal("2600"), new BigDecimal("100"),
                    client.getBank().getInterestRate(), randomEmployee,
                    clientService.getAllPaymentAccountsByClientId(client.getId()).get(random.nextInt(clientService.getAllPaymentAccountsByClientId(client.getId()).size()))
                );

                creditAccountService.create(creditAccount);
            }
        }
    }

    private static void handleCheckClientData(ClientService clientService, Scanner scanner) {

        System.out.println("Number of clients in the system: " + clientService.fetchAll().size());

        for (Client client : clientService.fetchAll()) {
            System.out.println("id: " + client.getId() + " - " + client.getName());
        }

        System.out.println("Enter client id:");

        int clientIdToPrint = scanner.nextInt();
        scanner.nextLine();
        clientService.printClientData(clientIdToPrint, true);
    }

    private static void handleCheckBankData(BankService bankService, Scanner scanner) {

        System.out.println("Number of banks in the system: " + bankService.fetchAll().size());

        for (Bank bank : bankService.fetchAll()) {
            System.out.println("id: " + bank.getId() + " - " + bank.getName());
        }

        System.out.println("Enter bank id:");

        int bankIdToPrint = scanner.nextInt();
        scanner.nextLine();
        bankService.printData(bankIdToPrint);
    }
}
