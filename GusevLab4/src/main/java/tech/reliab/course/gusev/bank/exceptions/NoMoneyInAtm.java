package tech.reliab.course.gusev.bank.exceptions;

public class NoMoneyInAtm extends RuntimeException {
    public NoMoneyInAtm(String string) {
        super(string);
    }
}
