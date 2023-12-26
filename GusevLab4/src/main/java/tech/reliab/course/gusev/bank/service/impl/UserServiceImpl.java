package tech.reliab.course.gusev.bank.service.impl;

import tech.reliab.course.gusev.bank.entity.*;
import tech.reliab.course.gusev.bank.exceptions.*;
import tech.reliab.course.gusev.bank.service.*;
import tech.reliab.course.gusev.bank.utils.Utils;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;



public class UserServiceImpl implements UserService {

    Map<Integer, User> tableUsers = new HashMap<Integer, User>();


    public void createPayment(Scanner in, UserService userService, PaymentAccountService paymentAccountService,
    User user, Bank bank) throws IOException {
        String data;
        for (int i = 0; i < 4; i++) {
             data = in.nextLine();
        }
        for (int i = 0; i < 3; i++) {
            data = in.next();
        }
        Double varDouble = in.nextDouble();
        userService.addBank(user, bank);
        PaymentAccount account = paymentAccountService.create(user, bank, BigDecimal.valueOf(varDouble));
        userService.addPaymentAccount(user, account);
        data = in.nextLine();
        data = in.nextLine();
    }

    public Employee findEmployee(List<Employee> lst, String name) {
        for (Employee employee : lst) {
            if (employee.getFullName().equals(name))
                return employee;
        }
        return null;
    }

    public void createCredit(Scanner in,
                             UserService userService,
                             CreditAccountService creditAccountService,
                             PaymentAccountService paymentAccountService,
                             EmployeeService employeeService,
                             User user,
                             Bank bank) {
        String data;
        for (int i = 0; i < 4; i++) {
            data = in.nextLine();
        }
        for (int i = 0; i < 3; i++) {
            data = in.next();
        }
        Integer day, month, year;
        String str;
//        in.useDelimiter(".");
        str = in.next();
        String[] array = str.split("\\.");
        day = Integer.parseInt(array[0]);
        month = Integer.parseInt(array[1]);
        year = Integer.parseInt(array[2]);
//        in.useDelimiter("\n");
        Calendar openCalendar = new GregorianCalendar(year, month, day);
        Date open = openCalendar.getTime();

        for (int i = 0; i < 3; i++) {
            data = in.next();
        }
//        in.useDelimiter(".");
        str = in.next();
        array = str.split("\\.");
        day = Integer.parseInt(array[0]);
        month = Integer.parseInt(array[1]);
        year = Integer.parseInt(array[2]);
//        in.useDelimiter("\n");
        Calendar closeCalendar = new GregorianCalendar(year, month, day);
        Date close = closeCalendar.getTime();

        String during = in.nextLine();
        during = in.nextLine();
        str = in.next();
        str = in.next();
        Double sum = in.nextDouble();
        str = in.next();
        str = in.next();
        Double payment = in.nextDouble();
        str = in.next();
        str = in.next();
        Double interestRate = in.nextDouble();
        str = in.nextLine();
        String worker = in.nextLine();
        array = worker.split(" ");
        worker = array[3] + " " + array[4];
        List<PaymentAccount> lst = user.getPaymentAccountsFilterOfBank(bank);
        PaymentAccount paymentAccount;
        if (lst.size() == 0) {
            userService.addBank(user, bank);
            paymentAccount = paymentAccountService.create(user, bank, BigDecimal.ZERO);
            userService.addPaymentAccount(user, paymentAccount);
            lst.add(paymentAccount);
        } else {
            paymentAccount = lst.get(0);
        }
        List<Employee>employeeLst = employeeService.getAll();
        Employee employee = findEmployee(employeeLst, worker);
        CreditAccount crAccount = creditAccountService.create(user, bank.getName(), open, close,
                BigDecimal.valueOf(sum), BigDecimal.valueOf(payment),
                BigDecimal.valueOf(interestRate), employee, paymentAccount);
        userService.addCreditAccount(user, crAccount);
        str = in.nextLine();
        str = in.nextLine();
    }

    Boolean readObject(Scanner in,
                       CreditAccountService creditAccountService,
                       PaymentAccountService paymentAccountService,
                       EmployeeService employeeService,
                       User user,
                       UserService userService,
                       Bank bank) throws IOException {
        String res = in.nextLine();
        if (res.isEmpty())
            return false;
        else if (res.equals("Объект: денежный счет"))
            this.createPayment(in, userService, paymentAccountService, user, bank);
        else if (res.equals("Объект: кредитный счет"))
            this.createCredit(in, userService, creditAccountService,
                    paymentAccountService, employeeService, user, bank);
        return true;
    }

    @Override
    public Boolean readAccountFromTxt(UserService userService, BankService bankService,
                                      Integer userId, Integer bankId, String file,
                                      CreditAccountService creditAccountService,
                                      PaymentAccountService paymentAccountService,
                                      EmployeeService employeeService) throws FileNotFoundException {
        if (userService == null || bankService == null)
            return false;
        User user = userService.getUserByHisId(userId);
        Bank bank = bankService.getBank(bankId);

        File varFile = new File(file);
        Scanner sc = new Scanner(varFile);
        Boolean flag = true;
        try {
            while (flag) {
                flag = this.readObject(sc, creditAccountService, paymentAccountService, employeeService, user,
                        userService, bank);
            }
        } catch (NoSuchElementException e) {
            System.out.print("Чтение закончено по причине достижения конца файла\n");
        }
        catch (RuntimeException e) {
            System.out.print(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return true;
    }

    @Override
    public Boolean writeAccountIntoTxt(UserService userService, BankService bankService, Integer userId, Integer bankId, String file) throws IOException {
        if (userService == null || bankService == null)
            return false;
        User user = userService.getUserByHisId(userId);
        Bank bank = bankService.getBank(bankId);

        List<PaymentAccount> lstAccounts = user.getPaymentAccountsFilterOfBank(bank);
        List<CreditAccount> lstCredit = user.getCreditAccountsFilterOfBank(bank);

        if (lstAccounts.size() == 0 && lstCredit.size() == 0)
            return false;
        OutputStream outputStream = new FileOutputStream(file);
        Writer outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

        for (PaymentAccount account: lstAccounts) {
            outputStreamWriter.write(account.toString());
        }
        for (CreditAccount account: lstCredit) {
            outputStreamWriter.write(account.toString());
        }
        outputStreamWriter.close();

        return true;
    }

    @Override
    public Bank chooseBestBank(BankService bankService, BigDecimal sum) {
        List<Bank> banks = bankService.getAllBanks();
        Bank res = Collections.max(banks);
        return res;
    }

    @Override
    public Boolean takeCredit(UserService userService, BankService bankService,
                              BankOfficeService bankOfficeService,
                              PaymentAccountService paymentAccountService,
                              CreditAccountService creditAccountService,
                              Integer userId, BigDecimal sum) {
        System.out.print("Запущена функция выдачи кредита\n");
        if (userService == null || bankService == null || userId == null || sum == null)
            throw new NullPointerException("В функцию takeCredit передан null");
        System.out.print("Пройдена null проверка\n");
        if (sum.doubleValue() < 0) {
            throw new NegativeCreditSum("Передано отрицательное значение для кредита " + sum.toString()
            + " в то время как ожидалось положительное значение");
        }
        System.out.print("Пройдена value проверка\n");
        User user = userService.getUserByHisId(userId);
        Bank bank = userService.chooseBestBank(bankService, sum);
        BankOffice bankOffice;
        try {
            bankOffice = chooseSuitableOffice(bankService.getLstOffices(bank), sum);
        } catch (CantFindGoodOffice e) {
            System.out.print("Невозможно найти офис, который мог бы дать кредит требуемой суммы\n");
            return false;
        }
        Employee employee = chooseSuitableEmployee(bankOfficeService.getWorkers(bankOffice));
        System.out.print(String.format("Получены банк - %s, юзер, офис и работник\n", bank.getName()));
        userService.addBank(user, bank);
        System.out.print("Банк добавлен к пользователю\n");
        PaymentAccount paymentAccount;
        try {
            paymentAccount = findAccount(user, bank);
        } catch (CantFindAccount e) {
            paymentAccount = paymentAccountService.create(user, bank, user.getSalary());
            userService.addPaymentAccount(user, paymentAccount);
            System.out.print("Денежный счет добавлен пользователю в выбранном банке\n");
        }
        try {
            System.out.print("Проверка рейтинга\n");
            checkRating(user, bank);
            System.out.print("Проверка рейтинга пройдена\n");
        } catch (DenyCreadit e) {
            System.out.print("Проверка рейтинга не пройдена, отказ в выдаче кредита\n");
            return false;
        }
        BankAtm bankAtm = null;
        System.out.print("Старт поиска банкомата для выдачи кредита\n");
        try {
            bankAtm = findAtm(bankOffice.getLstBankAtm(), sum);
        } catch (NoMoneyInAtm e) {
            System.out.print("Поиска банкомата в выбранном офисе не дал результата, проверяем другие офисы\n");
            for (BankOffice el : bankService.getLstOffices(bank)) {
                try {
                    if (el != bankOffice) {
                        bankAtm = findAtm(bankOffice.getLstBankAtm(), sum);
                    }
                } catch (NoMoneyInAtm e2) {
                    System.out.print("Поиска банкомата в i-том офисе не дал результата, проверяем другие офисы\n");
                }
            }
        }
        if (bankAtm == null) {
            System.out.print("Банкомат с нужным количеством наличности не обнаружен\n");
            return false;
        }
        System.out.print("Был обнаружен банкомат с требуемой наличностью, осуществляем процедуру списания и выдачи кредита\n");
        bank.setMoney(BigDecimal.valueOf(bank.getMoney().doubleValue() - sum.doubleValue()));
        bankAtm.setMoney(BigDecimal.valueOf(bankAtm.getMoney().doubleValue() - sum.doubleValue()));
        CreditAccount credAcc = creditAccountService.create(user, bank.getName(), new Date(), Utils.shift(new Date()),
                    sum, BigDecimal.valueOf(sum.doubleValue()/10),
                    bank.getInterestRate(),
                    employee, paymentAccount);
        userService.addCreditAccount(user, credAcc);


        return true;
    }

    public BankAtm findAtm(List<BankAtm> collection, BigDecimal sum) {
        int i = 0;
        while(collection.get(i).getMoney().doubleValue() <= sum.doubleValue())
            i++;
        if (i == collection.size())
            throw new NoMoneyInAtm("В банкоматах офиса не обнаружена требуемая сумма");
        return collection.get(i);
    }

    public void checkRating(User user, Bank bank) {
        if (user.getRating().doubleValue() < BigDecimal.valueOf(5000).doubleValue() && bank.getInterestRate().doubleValue() > 50)
            throw new DenyCreadit("Нарушено условие выдачи кредита");
        return;
    }

    public PaymentAccount findAccount(User user, Bank bank) {
        List <PaymentAccount>collection = user.getPaymentAccounts();
        int i = 0;
        while (i < collection.size()) {
            if (collection.get(i).getBank() == bank) {
                break;
            }
            i++;
        }
        if (i == collection.size()) {
            throw new CantFindAccount("У данного клиента нет счета данного банка\n");
        }
        return collection.get(i);
    }

    public Boolean check(BankOffice bankOffice, BigDecimal sum) {
        if (bankOffice.getWork() &&
                bankOffice.getGiveCredit() &&
                bankOffice.getMoney().doubleValue() >= sum.doubleValue())
            return true;
        return false;
    }

    @Override
    public BankOffice chooseSuitableOffice(List<BankOffice> collection, BigDecimal sum) {
        int i = 0;
        while (i < collection.size()) {
            if (check(collection.get(i), sum))
                break;
            i++;
        }
        if (i == collection.size()) {
            throw new CantFindGoodOffice("Не найден офис удовлетворяющий условиям");
        }
        return collection.get(i);
    }

    public Employee chooseSuitableEmployee(List<Employee> collection) {
        int i = 0;
        while (i < collection.size()) {
            if (collection.get(i).getCreditPossibility())
                break;
        }
        if (i == collection.size()) {
            throw new CantFindGoodEmployee("Не найден работник удовлетворяющий условиям");
        }
        return collection.get(i);
    }

    @Override
    public User getUserByHisId(Integer id) {
        return tableUsers.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<User>(tableUsers.values());
    }

    @Override
    public Boolean addBank(User user, Bank bank) {
        if (user != null && bank != null) {
            if (!user.getBanks().contains(bank)) {
                user.getBanks().add(bank);
                return true;
            }
        }
        return false;
    }

    @Override
    public User create(String fullName, Date birthday, String work, BigDecimal salary, BigDecimal rating) {
        User newUser = new User(fullName, birthday, work, salary,
                rating);
        tableUsers.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public Boolean addCreditAccount(User user, CreditAccount creditAccount) {
        if (user != null && creditAccount != null) {
            if (!user.getCreditAccounts().contains(creditAccount)) {
                user.getCreditAccounts().add(creditAccount);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean addPaymentAccount(User user, PaymentAccount paymentAccount) {
        if (user != null && paymentAccount != null) {
            if (!user.getPaymentAccounts().contains(paymentAccount)) {
                user.getPaymentAccounts().add(paymentAccount);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteBank(User user, Bank bank) {
        if (user != null && bank != null) {
            return user.getBanks().remove(bank);
        }
        return false;
    }

    @Override
    public Boolean deleteCreditAccount(User user, CreditAccount creditAccount) {
        if (user != null && creditAccount != null) {
            return user.getCreditAccounts().remove(creditAccount);
        }
        return false;
    }

    @Override
    public Boolean deletePaymentAccount(User user, PaymentAccount paymentAccount) {
        if (user != null && paymentAccount != null) {
            return user.getPaymentAccounts().remove(paymentAccount);
        }
        return false;
    }
}
