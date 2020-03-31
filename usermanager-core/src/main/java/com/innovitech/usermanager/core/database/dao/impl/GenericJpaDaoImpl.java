package com.innovitech.usermanager.core.database.dao.impl;

import com.innovitech.usermanager.core.database.dao.GenericJpaDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericJpaDaoImpl<T> implements GenericJpaDao<T> {

    private Class<T> entityClass;

    @PersistenceContext(unitName = "MyPU")
    protected EntityManager entityManager;

    public GenericJpaDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Transactional
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    public T find(Object primaryKey) {
        return entityManager.find(entityClass, primaryKey);
    }

    public List<T> findAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
        return typedQuery.getResultList();
    }

    @Transactional
    public void remove(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

}
