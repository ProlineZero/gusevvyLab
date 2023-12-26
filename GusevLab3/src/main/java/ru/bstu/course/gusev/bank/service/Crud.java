package ru.bstu.course.gusev.bank.service;

import java.util.List;

public interface Crud<T> {

    T findById(Integer id);

    List<T> fetchAll();

    T create(T entity);

    void delete(T entity);

    void update(T entity);

}
