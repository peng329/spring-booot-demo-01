package com.peng.springbootmall.service.impl;

import com.peng.springbootmall.dao.ProductDao;
import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductEntity getById(Integer productId) {
        return productDao.getById(productId);
    }

    @Override
    public Integer createProduct(ProductDto productDto) {
        return productDao.createProduct(productDto);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteById(productId);
    }

    @Override
    public void updateProductById(Integer productId, ProductDto productDto) {
        productDao.updateProductById(productId, productDto);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productDao.getProducts();
    }
}
