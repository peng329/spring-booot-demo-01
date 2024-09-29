package com.peng.springbootmall.service;

import com.peng.springbootmall.model.ProductEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


public interface ProductService {

    ProductEntity getById(Integer productId);
}
