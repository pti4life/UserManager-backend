package com.innovitech.usermanager.core.database.dao.impl;

import com.innovitech.usermanager.core.database.dao.UserDao;
import com.innovitech.usermanager.core.database.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class UserDaoImpl extends GenericJpaDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                "WHERE u.email=:email", User.class);

        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
