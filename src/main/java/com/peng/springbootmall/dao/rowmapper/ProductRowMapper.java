package com.peng.springbootmall.dao.rowmapper;

import com.peng.springbootmall.constant.ProductCategory;
import com.peng.springbootmall.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<ProductEntity> {


    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductId(rs.getInt("product_id"));
        productEntity.setProductName(rs.getString("product_name"));
        productEntity.setCategory(ProductCategory.valueOf(rs.getString("category")));
        productEntity.setImageUrl(rs.getString("image_url"));
        productEntity.setPrice(rs.getInt("price"));
        productEntity.setStock(rs.getInt("stock"));
        productEntity.setDescription(rs.getString("description"));
        productEntity.setCreatedDate(rs.getTimestamp("created_date"));
        productEntity.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return productEntity;







    }
}
