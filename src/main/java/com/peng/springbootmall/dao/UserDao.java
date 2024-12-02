package com.peng.springbootmall.dao;

import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;

public interface UserDao {

    //取得特定會員資料
    UserEntity getById(Integer userId);

    //註冊會員
    Integer userRegister(UserDto userDto);
}
