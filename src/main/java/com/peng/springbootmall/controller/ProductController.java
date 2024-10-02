package com.peng.springbootmall.controller;

import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    ResponseEntity<ProductEntity> getById(@PathVariable Integer productId){
        ProductEntity productEntity = productService.getById(productId);

        if(productEntity != null){
            return ResponseEntity.status(HttpStatus.OK).body(productEntity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
