package tech.reliab.course.gusev.bank.exceptions;

public class CantFindAccount extends RuntimeException{
    public CantFindAccount(String string) {
        super(string);
    }
}
