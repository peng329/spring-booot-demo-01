package com.peng.springbootmall.controller;

import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;
import com.peng.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //取得特定會員資料
    @GetMapping("/user/{userId}")
    ResponseEntity<UserEntity> getById(@PathVariable Integer userId){
        UserEntity userEntity = userService.getById(userId);

        if(userEntity != null){
            System.out.println("1");
            return ResponseEntity.status(HttpStatus.OK).body(userEntity);
        }else{
            System.out.println("2");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    };

    //註冊會員
    @PostMapping("/user/register")
    ResponseEntity<UserEntity> userRegister(@RequestBody @Valid UserDto userDto){
        Integer userId= userService.userRegister(userDto);

        if(userId != null){
            UserEntity userEntity = userService.getById(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }
}
