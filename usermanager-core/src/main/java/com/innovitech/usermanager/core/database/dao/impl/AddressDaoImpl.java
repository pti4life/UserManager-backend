package com.innovitech.usermanager.core.database.dao.impl;


import com.innovitech.usermanager.core.database.dao.AddressDao;
import com.innovitech.usermanager.core.database.entity.Address;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AddressDaoImpl extends GenericJpaDaoImpl<Address> implements AddressDao {

    public AddressDaoImpl() {
        super(Address.class);
    }

    @Override
    public List<Address> findByUserId(Long id) {
        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM Address a " +
                "WHERE a.user.id=:userId", Address.class);

        query.setParameter("userId", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void removeByUserId(Long userId) {
        Query query = entityManager.createQuery("DELETE FROM Address a WHERE a.user.id = :userId ");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

}
