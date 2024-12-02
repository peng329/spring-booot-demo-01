package com.peng.springbootmall.dao.rowmapper;

import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.model.UserEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(rs.getInt("user_id"));
        userEntity.setEmail(rs.getString("email"));
        userEntity.setPassword(rs.getString("password"));
        userEntity.setCreatedDate(rs.getTimestamp("created_date"));
        userEntity.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return userEntity;

    }
}
