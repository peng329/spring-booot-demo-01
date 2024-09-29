package com.peng.springbootmall.dao;

import com.peng.springbootmall.model.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

public interface ProductDao {
    ProductEntity getById(Integer productId) ;
}
