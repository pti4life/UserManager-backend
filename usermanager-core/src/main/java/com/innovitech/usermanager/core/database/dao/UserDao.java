package com.innovitech.usermanager.core.database.dao;

import com.innovitech.usermanager.core.database.entity.User;

public interface UserDao extends GenericJpaDao<User> {

    User findByEmail(String email);
}
