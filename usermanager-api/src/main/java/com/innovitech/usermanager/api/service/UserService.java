package com.innovitech.usermanager.api.service;

import com.innovitech.usermanager.api.vo.UserVO;

import java.util.List;

public interface UserService {

    List<UserVO> findAll();

    void persist(UserVO user);

    void remove(Long userId);

    void update(UserVO user);

    void authenticate(UserVO user);

}
