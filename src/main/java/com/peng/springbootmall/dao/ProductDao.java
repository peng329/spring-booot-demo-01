package com.peng.springbootmall.dao;

import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.model.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

public interface ProductDao {

    // 取得商品
    ProductEntity getById(Integer productId) ;

    // 創建商品
    Integer createProduct(ProductDto productDto);

    // 刪除商品
    void deleteById(Integer productId);

    // 修改商品
    void updateProductById(Integer productId, ProductDto productDto);
}
