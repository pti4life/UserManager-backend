package com.innovitech.usermanager.api.service;

import com.innovitech.usermanager.api.vo.AddressVO;

import java.util.List;

public interface AddressService {

    List<AddressVO> findByUserId(Long userId);

    void save(AddressVO address);

    void remove(Long addressId);

    void update(AddressVO address);
}
