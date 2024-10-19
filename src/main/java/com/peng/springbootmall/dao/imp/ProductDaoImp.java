package com.peng.springbootmall.dao.imp;

import com.peng.springbootmall.dao.ProductDao;
import com.peng.springbootmall.dao.rowmapper.ProductRowMapper;
import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.model.ProductEntity;
import org.hibernate.validator.internal.engine.groups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Date;
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

    @Override
    public Integer createProduct(ProductDto productDto) {
        String sql ="INSERT INTO product (product_name, category, image_url, price, stock, description, created_date," +
                " last_modified_date) VALUES (:productName, :category, :imageUrl," +
                " :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName",productDto.getProductName());
        map.put("category",productDto.getCategory().name());
        map.put("imageUrl",productDto.getImageUrl());
        map.put("price",productDto.getPrice());
        map.put("stock",productDto.getStock());
        map.put("description",productDto.getDescription());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map) , keyHolder );

        Integer productId = keyHolder.getKey().intValue();
        return productId;
    }

    @Override
    public void deleteById(Integer productId) {

        String sql = "delete from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void updateProductById(Integer productId, ProductDto productDto) {
        String sql = "update product set product_name = :productName, category = :category, image_url = :imageUrl, price = :price, " +
                "stock = :stock, description = :description, created_date = :createdDate, last_modified_date = :lastModifiedDate " +
                "where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productName",productDto.getProductName());
        map.put("category",productDto.getCategory().name());
        map.put("imageUrl",productDto.getImageUrl());
        map.put("price",productDto.getPrice());
        map.put("stock",productDto.getStock());
        map.put("description",productDto.getDescription());
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<ProductEntity> getProducts() {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date from product";

        Map<String, Object> map = new HashMap<>();

        List<ProductEntity> productEntityList = namedParameterJdbcTemplate.query(sql, map, productRowMapper);

        if (productEntityList.size() > 0) {
            return productEntityList;
        } else {
            return null;
        }
    }
}
