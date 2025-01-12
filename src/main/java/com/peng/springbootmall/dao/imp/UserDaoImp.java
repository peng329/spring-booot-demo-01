package com.peng.springbootmall.dao.imp;

import com.peng.springbootmall.dao.UserDao;
import com.peng.springbootmall.dao.rowmapper.UserRowMapper;
import com.peng.springbootmall.dto.UserDto;
import com.peng.springbootmall.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
    @Autowired
    private UserRowMapper userRowMapper;

    @Override
    public UserEntity getById(Integer userId) {
        String sql = "select user_id, email, password, created_date, last_modified_date from user where user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);

        List<UserEntity> userEntityList = namedParameterJdbcTemplate.query(sql,map,userRowMapper);

        if(userEntityList.size() > 0){
            return userEntityList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public UserEntity getByEmail(String email) {
        String sql = "select user_id, email, password, created_date, last_modified_date from user where email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email",email);

        List<UserEntity> userEntityList = namedParameterJdbcTemplate.query(sql,map,userRowMapper);

        if(userEntityList.size() > 0){
            return userEntityList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createUser(UserDto userDto) {
        String sql ="INSERT INTO user (email, password, created_date, last_modified_date)" +
                "  VALUES (:email, :password, :createdDate, :lastModifiedDate)";

        System.out.println("---" );

        Map<String, Object> map = new HashMap<>();
        map.put("email",userDto.getEmail());
        map.put("password",userDto.getPassword());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        System.out.println("---" + userDto.getEmail());
        System.out.println("---" + userDto.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map) , keyHolder );

        Integer userId = keyHolder.getKey().intValue();

        System.out.println("---" + userId);

        return userId;
    }
}
