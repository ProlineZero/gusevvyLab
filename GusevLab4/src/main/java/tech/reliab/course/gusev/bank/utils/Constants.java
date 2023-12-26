package tech.reliab.course.gusev.bank.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Constants {
    static public List<String> BANK_NAMES = new ArrayList<String>(){{
        add("Газпромбанк");
        add("Тинькоф");
        add("Сбербанк");
        add("ВТБ");
        add("Ozon банк");
    }};
    static public List<String> ATM_NAMES;
    static public List<String> OFFICE_ADDRESS = new ArrayList<String>() {{
        add("г. Белгород, ул. Костюкова, д. 10");
        add("г. Старый Оскол, ул. Ленина, д. 44");
        add("г. Старый Оскол, мкр. Приборостроитель, д. 55");
        add("г. Старый Оскол, ул. Комсомольская, д. 61");
        add("г. Старый Оскол, бул. Дружбы, д. 2");
        add("г. Старый Оскол, мкр. Солнечный, д. 3");
        add("г. Старый Оскол, ул. Ленина, д. 74");
        add("г. Старый Оскол, мкр. Макаренко, д. 38");
        add("г. Старый Оскол, мкр. Звездный, д. 1");
        add("г. Старый Оскол, мкр. Рудничный, д. 14");
        add("г. Белгород, просп. Белгородский, д. 73");
        add("г. Белгород, Магистральная улица, 12");
        add("г. Белгород, просп. Гражданский, д. 36");
        add("г. Белгород, просп. Хмельницкого, 133Г");
        add("г. Белгород, ул. Победы, 124");
        add("г. Белгород, просп. Белгородский, 54");
    }};
    static public List<String> FULLNAMES = new ArrayList<String>(){{
        add("James Smith");
        add("David Smith");
        add("Robert Smith");
        add("John Smith");
        add("Maria Garcia");
        add("Michael Smith");
        add("James Johnson");
        add("David Johnson");
        add("Maria Rodriguez");
        add("Mary Smith");
        add("Maria Hernandez");
        add("Charles Smith");
        add("Maria Martinez");
        add("Robert Johnson");
        add("James Williams");
        add("James Brown");
        add("Jose Garcia");
        add("Maria Gonzalez");
        add("Jose Rodriguez");
        add("David Brown");
        add("Carla Houston");
        add("Kaitlyn Kristy");
        add("Sally Selcen Stochliya");
        add("Abigail Akon Obro");
        add("Candice Cromwell");
        add("Иванов Матвей Иванович");
        add("Симонова Ника Марковна");
        add("Леонов Ибрагим Маркович");
        add("Аникина Мария Львовна");
        add("Михайлова Мария Марковна");
        add("Зверев Никита Эмирович");
        add("Колесников Андрей Кириллович");
        add("Морозов Илья Иванович");
        add("Цветков Али Захарович");
        add("Корнеев Максим Алексеевич");
        add("Захарова Ева Макаровна");
        add("Белов Адам Георгиевич");
        add("Лаптев Андрей Ярославович");
        add("Ефимов Александр Лукич ");
        add("Козлов Артём Константинович");
        add("Волкова Ева Артёмовна");
        add("Бирюков Артемий Тимофеевич");
        add("Федотов Глеб Дмитриевич");
        add("Полякова Анастасия Фёдоровна");
        add("Мартынова Софья Дмитриевна");
        add("Родионов Ярослав Даниилович");
        add("Киселев Мирон Сергеевич");
        add("Краснов Михаил Львович");
        add("Игнатов Александр Савельевич");
        add("Козырев Иван Петрович");}
    };

    static public List<String> POSITIONS = new ArrayList<String>(){{
        add("Кассир");
        add("Cпециалист по обслуживанию");
        add("Cотрудник кредитного отдела");
        add("Работник валютного подразделения");
        add("Консультант");
        add("Управляющий");
        add("Бухгалтер банка");
        add("Финансовый аналитик");
        add("Аналитик фондового рынка");
        add("Аудитор");
        add("Банкир");}
    };

    static public Integer RATING_PATTERN = 10000;

    static public Integer SALARY_PATTERN = 45000;
    static public Integer RENTAL_OFFICE_MAX_PRICE = 2000;
    static public Integer MAINTENANCE_COST_OF_ATM = 3000;

    static public Integer NUMBER_OFFICES = 3;
    static public Integer NUMBER_BANKS = 5;

    static public Integer NUMBER_USERS = 5;

    static public Integer NUMBER_ATMS = 3;

    static public Integer NUMBER_COUNTERS = 2;

    static public Integer PAYMENT_ACCOUNT_SUM_PATTERN = 100000;

    static public Integer CREDIT_PATTERN = 1000000;

    static public Integer NUMBER_EMPLOYEES_IN_OFFICE = 5;

    static public Integer NUMBER_FULLNAMES = FULLNAMES.size();

    static public Integer NUMBER_POSITIONS = POSITIONS.size();

}
