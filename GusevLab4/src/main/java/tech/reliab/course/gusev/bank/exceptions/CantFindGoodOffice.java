package tech.reliab.course.gusev.bank.exceptions;

public class CantFindGoodOffice extends RuntimeException {
    public CantFindGoodOffice(String str) {
        super(str);
    }
}
