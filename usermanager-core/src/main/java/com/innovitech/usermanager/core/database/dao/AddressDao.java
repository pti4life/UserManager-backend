package com.innovitech.usermanager.core.database.dao;

import com.innovitech.usermanager.api.vo.UserVO;
import com.innovitech.usermanager.core.database.entity.Address;

import java.util.List;

public interface AddressDao extends GenericJpaDao<Address> {

    List<Address> findByUserId(Long id);

    void removeByUserId(Long userId);

}
