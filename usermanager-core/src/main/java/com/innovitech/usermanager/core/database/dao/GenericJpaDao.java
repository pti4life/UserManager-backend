package com.innovitech.usermanager.core.database.dao;

import java.util.List;

public interface GenericJpaDao<T> {

    void persist(T entity);

    T find(Object primaryKey);

    List<T> findAll();

    void remove(T entity);

    void update(T entity);
}
