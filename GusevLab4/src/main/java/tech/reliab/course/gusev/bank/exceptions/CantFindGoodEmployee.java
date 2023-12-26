package tech.reliab.course.gusev.bank.exceptions;

public class CantFindGoodEmployee extends RuntimeException{
    public CantFindGoodEmployee(String str) {
        super(str);
    }
}
