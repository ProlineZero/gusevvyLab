package tech.reliab.course.gusev.bank.service;

import tech.reliab.course.gusev.bank.entity.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    public User create(String fullName, Date birthday, String work, BigDecimal salary, BigDecimal rating);

    public List<User> getUsers();


    public Boolean readAccountFromTxt(UserService userService,
                                      BankService bankService,
                                      Integer userId,
                                      Integer bankId,
                                      String file,
                                      CreditAccountService creditAccountService,
                                      PaymentAccountService paymentAccountService,
                                      EmployeeService employeeService) throws FileNotFoundException, UnsupportedEncodingException;
    public Boolean writeAccountIntoTxt(UserService userService,
                                       BankService bankService,
                                       Integer userId,
                                       Integer bankId,
                                       String file) throws IOException;

    public Boolean takeCredit(UserService userService,
                              BankService bankService,
                              BankOfficeService bankOfficeService,
                              PaymentAccountService paymentAccountService,
                              CreditAccountService creditAccountService,
                              Integer userId,
                              BigDecimal sum);

    public Bank chooseBestBank(BankService bankService, BigDecimal sum);

    public BankOffice chooseSuitableOffice(List<BankOffice> collection, BigDecimal sum);

    public Employee chooseSuitableEmployee(List<Employee> collection);

    public User getUserByHisId(Integer id);

    public Boolean addBank(User user, Bank bank);
    public Boolean deleteBank(User user, Bank bank);

    public Boolean addCreditAccount(User user, CreditAccount creditAccount);
    public Boolean deleteCreditAccount(User user, CreditAccount creditAccount);

    public Boolean addPaymentAccount(User user, PaymentAccount paymentAccount);
    public Boolean deletePaymentAccount(User user, PaymentAccount paymentAccount);
}
