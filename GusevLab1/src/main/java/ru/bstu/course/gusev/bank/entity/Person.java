package ru.bstu.course.gusev.bank.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Person {

    protected UUID id;
    protected String name;
    protected LocalDate birthDate;

    public Person() {

        this.id = UUID.randomUUID();
        this.name = "No name";
        this.birthDate = null;
    }

    public Person(Person person) {

        this.id = UUID.fromString(person.id.toString());
        this.name = person.name;
        this.birthDate = person.birthDate;
    }

    public Person(String name, LocalDate birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person(UUID id, String name, LocalDate birthDate) {

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {

        return "Person:{" +
            "\n id='" + getId() + "'" +
            ",\n name='" + getName() + "'" +
            ",\n birthdDate='" + getBirthDate() + "'" +
            "\n}";
    }

}
