package com.peng.springbootmall.service.impl;

import com.peng.springbootmall.dao.UserDao;
import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;
import com.peng.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public UserEntity getById(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    public Integer userRegister(UserDto userDto) {
        return userDao.userRegister(userDto);
    }
}
