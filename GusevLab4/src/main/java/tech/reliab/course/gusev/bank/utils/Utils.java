package tech.reliab.course.gusev.bank.utils;

import java.util.Date;
import java.util.Random;

public class Utils {
    static Random rnd = new Random(1);

    public static String logic(Boolean a) {
        if (a) {
            return "истина";
        } else {
            return "ложь";
        }
    }
    public static Date randomDate() {
        long ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

    public static Date shift(Date d) {
        return new Date(d.getTime() + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000)));
    }

    public static double rand() {
        return rnd.nextDouble();
    }
}
