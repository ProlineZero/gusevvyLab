package ru.bstu.course.gusev.bank;

import ru.bstu.course.gusev.bank.entity.values.EmployeePost;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

import static java.lang.Long.bitCount;

public class Utils {

    public static final Random random = new Random();

    private static final String[] WORK_PLACES = {"Google", "Facebook", "Yandex", "Tinkoff", "VK Group"};
    private static final String[] NAMES = {
            "Андреев Александр Валентинович", "Кузнецова Екатерина Андреевна", "Петров Игорь Сергеевич",
            "Смирнова Ольга Александровна", "Иванов Дмитрий Николаевич", "Федорова Мария Анатольевна",
            "Григорьев Павел Владимирович", "Соколова Анастасия Игоревна", "Морозов Артем Валерьевич",
            "Лебедева Наталья Алексеевна", "Козлов Денис Александрович", "Сидорова Елена Сергеевна",
            "Новиков Артур Дмитриевич", "Тимофеева Алина Владимировна", "Белов Антон Игоревич",
            "Костина Ирина Петровна", "Жуков Владислав Александрович", "Соловьева Евгения Дмитриевна",
            "Алексеев Станислав Сергеевич", "Макарова Вера Артемовна"
    };

    public static EmployeePost getRandomEmployee() {

        EmployeePost[] employeePostValues = new EmployeePost[]{
            EmployeePost.DIRECTOR, EmployeePost.OPERATOR, EmployeePost.OFFICE_EMPLOYEE
        };

        return employeePostValues[random.nextInt(employeePostValues.length)];
    }

    public static String[] getNames() {
        return NAMES;
    }

    public static String[] getWorkPlaces() {
        return WORK_PLACES;
    }

    public static BigInteger nextBigInteger(int precision, Random r) {
        return new BigInteger(bitCount(precision), r);
    }

    public static BigDecimal nextBigDecimal(int scale, Random r) {

        BigInteger bi = nextBigInteger(scale, r); // generate random BigInteger with a number of digits equal to scale.
        BigDecimal bd = new BigDecimal(bi); // convert BigInteger to a BigDecimal

        return bd.movePointLeft(bd.precision()); // move the decimal point all the way to the left
    }

    public static BigDecimal nextBigDecimal(BigDecimal norm, int scale, Random r) {
        return norm.multiply(nextBigDecimal(scale, r), new MathContext((norm.precision() - norm.scale()) + scale));
    }

    public static BigDecimal between(BigDecimal min, BigDecimal MAX) {
        return between(min, MAX, random);
    }

    public static BigDecimal between(BigDecimal min, BigDecimal MAX, Random random) {
        return min.add(nextBigDecimal(MAX.subtract(min), Math.max(min.precision(), MAX.precision()), random));
    }
}
