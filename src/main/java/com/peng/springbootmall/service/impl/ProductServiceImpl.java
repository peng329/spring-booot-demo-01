package com.peng.springbootmall.service.impl;

import com.peng.springbootmall.dao.ProductDao;
import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;



    @Override
    public ProductEntity getById(Integer productId) {
        return productDao.getById(productId);
    }
}
