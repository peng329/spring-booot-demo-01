package com.peng.springbootmall.dao;

import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.dto.ProductSearch;
import com.peng.springbootmall.model.ProductEntity;

import java.util.List;
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

    //取得多筆商品
    List<ProductEntity> getProducts();

    //取得商品，且提供自訂查詢條件，例如商品類型，與商民名稱的關鍵字
    List<ProductEntity> getProductsBySearch(ProductSearch productSearch);

//    //排序商品
//    List<ProductEntity> getProductsOrder(String order);
}
