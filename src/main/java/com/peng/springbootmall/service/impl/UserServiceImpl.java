package com.peng.springbootmall.service.impl;

import com.mysql.cj.log.Log;
import com.peng.springbootmall.dao.UserDao;
import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;
import com.peng.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    final static private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserEntity getById(Integer userId) {
        return userDao.getById(userId);
    }

    @Override
    public Integer userRegister(UserDto userDto) {
        UserEntity userEntity = userDao.getByEmail(userDto.getEmail());
        if (userEntity != null){

            log.warn("email:{}已經被註冊",userDto.getEmail());

            //直接拋錯誤來終止
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return userDao.createUser(userDto);
        }
    }

    @Override
    public UserEntity userLogin(UserDto userDto) {
        UserEntity userEntity = userDao.getByEmail(userDto.getEmail());
        if (userEntity == null){

            log.warn("email:{}還沒註冊，輸入有誤",userDto.getEmail());
            //直接拋錯誤來終止
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //將密碼做 MD5 加密
        String encryptPw = DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes(StandardCharsets.UTF_8));

        if(!userEntity.getPassword().equals(encryptPw)){
            log.warn("密碼輸入有誤",userDto.getEmail());
            //直接拋錯誤來終止
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return userEntity;

    }
}
