package ru.bstu.course.gusev.bank.service;

public interface Crud<T> {

    T findOne(Integer id);

    T create(T entity);

    void delete(T entity);

    void update(T entity);

}
