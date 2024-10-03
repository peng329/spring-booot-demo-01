package com.peng.springbootmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/products")
    ResponseEntity<ProductEntity> createProduct(@RequestBody @Valid ProductDto productDto)  {

//        ObjectMapper objectMapper= new ObjectMapper();
//        ProductDto productDto = objectMapper.readValue(json, ProductDto.class);

        Integer productId = productService.createProduct(productDto);

        ProductEntity productEntity = productService.getById(productId);

        if(productEntity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(productEntity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    };
    
}
