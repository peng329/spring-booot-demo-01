package com.peng.springbootmall.service;

import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;

public interface UserService {
    UserEntity getById(Integer userId);

    Integer userRegister(UserDto userDto);

    UserEntity userLogin(UserDto userDto);
}
