package com.innovitech.usermanager.core.service.impl;

import com.innovitech.usermanager.api.service.AddressService;
import com.innovitech.usermanager.api.vo.AddressVO;
import com.innovitech.usermanager.core.database.dao.AddressDao;
import com.innovitech.usermanager.core.database.entity.Address;
import com.innovitech.usermanager.core.validator.AddressValidator;
import com.innovitech.usermanager.core.util.ObjectMapperUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AddressServiceImpl implements AddressService {

    @Inject
    private AddressDao addressDao;

    @Inject
    private AddressValidator addressValidator;


    @Override
    public List<AddressVO> findByUserId(Long userId) {
        return ObjectMapperUtils.mapAll(addressDao.findByUserId(userId), AddressVO.class);
    }

    @Override
    public void save(AddressVO address) {
        addressValidator.setCity(address.getCity());
        addressValidator.setHouseNumber(address.getHouseNumber());
        addressValidator.setPostalCode(address.getPostalCode());
        addressValidator.setStreet(address.getStreet());
        addressValidator.setUser(address.getUser());
        addressValidator.validateAll();

        Address addressEntity = ObjectMapperUtils.map(address, Address.class);
        addressDao.persist(addressEntity);
    }

    @Override
    public void remove(Long addressId) {
        Address addressEntity = addressDao.find(addressId);
        addressDao.remove(addressEntity);
    }

    @Override
    public void update(AddressVO address) {
        addressValidator.setCity(address.getCity());
        addressValidator.setHouseNumber(address.getHouseNumber());
        addressValidator.setPostalCode(address.getPostalCode());
        addressValidator.setStreet(address.getStreet());
        addressValidator.setUser(address.getUser());
        addressValidator.validateAll();

        Address addressEntity = ObjectMapperUtils.map(address, Address.class);
        addressDao.update(addressEntity);
    }
}
