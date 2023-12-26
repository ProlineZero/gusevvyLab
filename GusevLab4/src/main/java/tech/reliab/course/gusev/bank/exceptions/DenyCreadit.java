package tech.reliab.course.gusev.bank.exceptions;

public class DenyCreadit extends RuntimeException {
    public DenyCreadit(String string) {
        super(string);
    }
}
