package com.peng.springbootmall.dao.imp;

import com.peng.springbootmall.dao.ProductDao;
import com.peng.springbootmall.dao.rowmapper.ProductRowMapper;
import com.peng.springbootmall.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImp implements ProductDao {


//    private JdbcTemplate JdbcTemplate = new JdbcTemplate();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
    @Autowired
    private ProductRowMapper productRowMapper;

    @Override
    public ProductEntity getById(Integer productId) {

        String sql = "select product_id, product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        List<ProductEntity>  productEntityList = namedParameterJdbcTemplate.query(sql,map,productRowMapper);

        if(productEntityList.size() > 0){
            return productEntityList.get(0);
        }else{
            return null;
        }

    }
}
