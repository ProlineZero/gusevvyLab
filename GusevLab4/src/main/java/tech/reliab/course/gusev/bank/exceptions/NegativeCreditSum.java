package tech.reliab.course.gusev.bank.exceptions;

public class NegativeCreditSum extends IllegalArgumentException {
    public NegativeCreditSum(String str) {
        super(str);
    }
}
