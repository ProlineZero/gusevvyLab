package ru.bstu.course.gusev.bank;

import ru.bstu.course.gusev.bank.entity.*;
import ru.bstu.course.gusev.bank.entity.values.BankAtmStatusValues;
import ru.bstu.course.gusev.bank.entity.values.EmployeePostValues;
import ru.bstu.course.gusev.bank.service.*;
import ru.bstu.course.gusev.bank.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("Sbebra bank"));

        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        AtmService atmService = new AtmServiceImpl();
        ClientService userService = new ClientServiceImpl();
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();


        BankOffice bankOffice = bankOfficeService.create(
            new BankOffice(
                "Sbebra Bank Office", "Bebra Street", bank,
                true, true, 0, true, true,
                true, bank.getTotalMoney(), new BigDecimal("700")
            )
        );

        System.out.println(bankOffice);

        Employee employee = employeeService.create(
            new Employee(
                "Dmitriy Dmitriy Dmitriy",
                LocalDate.of(270, 2, 21),
                EmployeePostValues.OFFICE_EMPLOYEE, bank, true, bankOffice, true,
                new BigDecimal("10")
            )
        );

        System.out.println(employee);

        BankAtm bankAtm = atmService.create(
            new BankAtm(
                "Bebra ATM", bankOffice.getAddress(), BankAtmStatusValues.WORKING, bank,
                bankOffice, employee, true, true, new BigDecimal("0"),
                new BigDecimal("25")
            )
        );
        System.out.println(bankAtm);

        Client user = userService
            .create(
                new Client(
                    "Ivan Inva Ivan", LocalDate.of(264, 2, 15),
                    "Google", new BigDecimal("1000"), bank, new BigDecimal("999999999")
                )
            );

        System.out.println(user);

        PaymentAccount paymentAccount = paymentAccountService.create(
            new PaymentAccount(user, bank, new BigDecimal("9000"))
        );

        System.out.println(paymentAccount);

        CreditAccount creditAccount = creditAccountService.create(
            new CreditAccount(
                user, bank, LocalDate.of(298, 1, 1), LocalDate.of(302, 1, 1),
                48, new BigDecimal("29000"), new BigDecimal("29000"),
                new BigDecimal("100"), new BigDecimal("2"), employee, paymentAccount
            )
        );

        System.out.println(creditAccount);
    }
}
