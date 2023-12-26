package tech.reliab.course.gusev.bank;

import tech.reliab.course.gusev.bank.entity.*;
import tech.reliab.course.gusev.bank.exceptions.NegativeCreditSum;
import tech.reliab.course.gusev.bank.service.BankOfficeService;
import tech.reliab.course.gusev.bank.service.*;
import tech.reliab.course.gusev.bank.service.impl.*;
import tech.reliab.course.gusev.bank.utils.AtmStatus;
import tech.reliab.course.gusev.bank.utils.Constants;
import tech.reliab.course.gusev.bank.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        UserService userService = new UserServiceImpl();
        BankService bankService = new BankServiceImpl();
        AtmService atmService = new AtmServiceImpl();
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();

        for (int i = 0; i < 5; i++) {
            Bank bank = bankService.create(Constants.BANK_NAMES.get(i));
        }

        List<Bank> banksArray = bankService.getAllBanks();
        for (int i = 0; i < Constants.NUMBER_OFFICES*Constants.NUMBER_BANKS; i++) {
            BankOffice office = bankOfficeService.create(Constants.OFFICE_ADDRESS.get(i),
                    Boolean.TRUE,
                    Boolean.TRUE,
                    0,
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.TRUE,
                    BigDecimal.valueOf(banksArray.get(i / Constants.NUMBER_OFFICES).getMoney().doubleValue() / Constants.NUMBER_OFFICES),
                    BigDecimal.valueOf(Constants.RENTAL_OFFICE_MAX_PRICE * Utils.rand()));
            bankService.addOffice(banksArray.get(i / Constants.NUMBER_OFFICES), office);

            for (int j = 0; j < Constants.NUMBER_EMPLOYEES_IN_OFFICE; j++) {
                Employee employee = employeeService.create(Constants.FULLNAMES.get((int)
                        (Utils.rand() * Constants.NUMBER_FULLNAMES)), Utils.randomDate(), Constants.POSITIONS.get((int)
                        (Constants.NUMBER_POSITIONS * Utils.rand())), banksArray.get(i / 3), Boolean.TRUE, office, Boolean.TRUE,
                        BigDecimal.valueOf(Constants.SALARY_PATTERN * Utils.rand()));
                bankService.addEmployee(banksArray.get(i / 3), employee);
                bankOfficeService.addWorker(office, employee);
            }
            List<Employee> lst = employeeService.getWorkerList(banksArray.get(i / Constants.NUMBER_ATMS).getId());
            BankAtm atm = atmService.create(banksArray.get(i / Constants.NUMBER_ATMS), office,
                    lst.get(((int)(Constants.NUMBER_EMPLOYEES_IN_OFFICE * Utils.rand()))), AtmStatus.WORK,
                    true, true, BigDecimal.valueOf(Constants.MAINTENANCE_COST_OF_ATM));
            bankOfficeService.addAtm(office, atm);
            bankService.addAtm(banksArray.get(i / Constants.NUMBER_ATMS), atm);

        }
        List<Bank> lstBanks = bankService.getAllBanks();
        for (int i = 0; i < Constants.NUMBER_BANKS; i++) {
            for (int j = 0; j < Constants.NUMBER_USERS; j++) {
                User user = userService.create(
                        Constants.FULLNAMES.get((int)(Constants.NUMBER_FULLNAMES * Utils.rand())),
                        Utils.randomDate(),
                        Constants.POSITIONS.get((int)(Constants.NUMBER_POSITIONS * Utils.rand())),
                        BigDecimal.valueOf(Constants.SALARY_PATTERN*Utils.rand()),
                        BigDecimal.valueOf(Constants.RATING_PATTERN * Utils.rand()));
                bankService.addClient(lstBanks.get(i), user);
                userService.addBank(user, lstBanks.get(i));

                for (int k = 0; k < Constants.NUMBER_COUNTERS; k++) {
                    PaymentAccount pAccount = paymentAccountService.create(user, lstBanks.get(i),
                            BigDecimal.valueOf(Constants.PAYMENT_ACCOUNT_SUM_PATTERN*Utils.rand()));
                    userService.addPaymentAccount(user, pAccount);

                    Date startDate = Utils.randomDate();
                    Date finalDate = Utils.randomDate();
                    if (startDate.after(finalDate)) {
                        Date tmp = startDate;
                        startDate = finalDate;
                        finalDate = tmp;
                    }
                    CreditAccount account = creditAccountService.create(user, lstBanks.get(i).getName(),
                            startDate, finalDate, BigDecimal.valueOf(Utils.rand()*Constants.CREDIT_PATTERN),
                            BigDecimal.valueOf(15000), BigDecimal.valueOf(10),
                            employeeService.getWorkerList(lstBanks.get(i).getId()).get((int)(Utils.rand()*Constants.NUMBER_EMPLOYEES_IN_OFFICE)),
                            pAccount);
                    userService.addCreditAccount(user, account);
                }
            }
        }

        while (true) {
            System.out.print("Вас приветствует программа банкинга\n");
            System.out.print("Для того, чтобы получить информацию по банку введите \"банк <id>\"\n");
            System.out.print("Для того, чтобы получить всю информацию о клиенте введите \"пользователь <id>\"\n");
            System.out.print("Для того, чтобы попробовать выдать кредит введите \"кредит <id> <sum>\"\n");
            System.out.print("Для того, чтобы получить всю информацию введите \"все\"\n");
            System.out.print("Для того, чтобы завершить программу введите \"завершить\"\n");
            System.out.print("Для того, чтобы перекинуть счета пользователя в .txt файл введите \"поместить <Ид пользователя>" +
                    " <Ид банка> в <Путь к файлу>\"\n");
            System.out.print("Для того, чтобы загрузить счета пользователя в .txt файл введите \"загрузить <Ид пользователя>" +
                    " <Ид банка> из <Путь к файлу>\"\n");
            System.out.print("->");
            Scanner in = new Scanner(System.in);
            String command = in.next().toLowerCase();
            Integer userId;
            Bank bank;
            String str;
            switch (command) {
                case ("банк"):
                    Integer bankId = in.nextInt();
                    bank = bankService.getBank(bankId);
                    System.out.print(bank);
                    break;
                case ("пользователь"):
                    userId = in.nextInt();
                    System.out.print(userService.getUserByHisId(userId));
                    break;
                case("кредит"):
                    userId = in.nextInt();
                    BigDecimal sum = in.nextBigDecimal();
                    try {
                        userService.takeCredit(userService, bankService, bankOfficeService, paymentAccountService, creditAccountService, userId, sum);
                    } catch (NegativeCreditSum e) {
                        System.out.print(e);
                    }
                    break;
                case("все"):
                    for (int i = 0; i < Constants.NUMBER_BANKS; i++) {
                        System.out.print(lstBanks.get(i));

                        for (int j = 0; j < Constants.NUMBER_OFFICES; j++) {
                            List<BankOffice> lstOffices = bankService.getLstOffices(lstBanks.get(j));
                            System.out.print(lstOffices);
                            System.out.print(employeeService.getWorkerList(i));
                            for (BankOffice office : lstOffices) {
                                System.out.print(bankOfficeService.getAtms(office));
                            }
                        }
                        for (int j = 0; j < Constants.NUMBER_USERS; j++) {
                            List<User> lst = bankService.getLstUsers(lstBanks.get(i));
                            for (User item : lst) {
                                System.out.print(item);
                                System.out.print("Денежные счета: ");
                                for (PaymentAccount itemAccount : item.getPaymentAccounts())
                                    System.out.print(itemAccount);
                                System.out.print("Кредитные счета: ");
                                for (CreditAccount itemAccount : item.getCreditAccounts())
                                    System.out.print(itemAccount);
                            }
                        }

                    }
                    break;
                case ("завершить"):
                    return;
                case ("поместить"):
                    userId = in.nextInt();
                    bankId = in.nextInt();
                    str = in.next();
                    String file = in.next();
                    try {
                        userService.writeAccountIntoTxt(userService, bankService, userId, bankId, file);
                    } catch (IOException e) {
                        System.out.print(e);
                    }
                    break;
                case ("загрузить"):
                    userId = in.nextInt();
                    bankId = in.nextInt();
                    str = in.next();
                    file = in.next();
                    try {
                        userService.readAccountFromTxt(userService, bankService, userId, bankId, file, creditAccountService,
                                paymentAccountService, employeeService);
                    } catch (FileNotFoundException e) {
                        System.out.print(e);
                    } catch (UnsupportedEncodingException e) {
                        System.out.print(e);
                    }
                    break;
                default:
                    System.out.print("Не корректный ввод\n");
                    break;
            }

        }

    }
}