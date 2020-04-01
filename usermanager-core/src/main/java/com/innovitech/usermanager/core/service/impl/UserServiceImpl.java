package com.innovitech.usermanager.core.service.impl;

import com.innovitech.usermanager.api.exception.CustomException;
import com.innovitech.usermanager.api.service.UserService;
import com.innovitech.usermanager.api.vo.UserVO;
import com.innovitech.usermanager.core.database.dao.AddressDao;
import com.innovitech.usermanager.core.database.dao.UserDao;
import com.innovitech.usermanager.core.database.entity.User;
import com.innovitech.usermanager.core.util.ObjectMapperUtils;
import com.innovitech.usermanager.core.validator.UserValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private UserValidator userValidator;

    @Inject
    private AddressDao addressDao;


    @Override
    public List<UserVO> findAll() {
        return ObjectMapperUtils.mapAll(userDao.findAll(), UserVO.class);
    }

    @Override
    public void persist(UserVO user) {
        userValidator.setName(user.getName());
        userValidator.setEmail(user.getEmail());
        userValidator.setPassword(user.getPassword());
        userValidator.validateAll();
        User userEntity = userDao.findByEmail(user.getEmail());
        if (userEntity != null) {
            throw new CustomException("Ilyen e-mail címmel már van felhasználó", 400);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userEntity = ObjectMapperUtils.map(user, User.class);
        userDao.persist(userEntity);
    }

    @Override
    public void remove(Long userId) {
        User userEntity = userDao.find(userId);
        addressDao.removeByUserId(userId);
        userDao.remove(userEntity);
    }

    @Override
    public void update(UserVO user) {
        User userEntity = userDao.findByEmail(user.getEmail());
        if (userEntity != null) {
            throw new CustomException("Ilyen email címmel már van felhasználó!", 400);
        }
        String password = user.getPassword();
        String passFromDatabase = userDao.find(user.getId()).getPassword();
        if (!password.equals(passFromDatabase)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userEntity = ObjectMapperUtils.map(user, User.class);
        userDao.update(userEntity);
    }

    @Override
    public void authenticate(UserVO user) {
        User userEntity = userDao.findByEmail(user.getEmail());
        if (userEntity == null) {
            throw new CustomException("Nem található ilyen email címmel felhasználó", 400);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(user.getPassword(), userEntity.getPassword())) {
            throw new CustomException("Nem megfelelő jelszót adott meg", 400);
        }
    }
}
