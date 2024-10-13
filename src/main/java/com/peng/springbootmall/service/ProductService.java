package com.peng.springbootmall.service;

import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.model.ProductEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


public interface ProductService {

    // 查詢商品
    ProductEntity getById(Integer productId);

    // 創建商品
    Integer createProduct(ProductDto productDto);

    // 刪除商品
    void deleteProduct(Integer productId);

    //修改商品
    void updateProductById(Integer productId, ProductDto productDto);
}
