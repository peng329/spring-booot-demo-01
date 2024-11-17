package com.peng.springbootmall.controller;

import com.peng.springbootmall.constant.ProductCategory;
import com.peng.springbootmall.dto.ProductDto;
import com.peng.springbootmall.dto.ProductSearch;
import com.peng.springbootmall.model.ProductEntity;
import com.peng.springbootmall.service.ProductService;
import com.peng.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;

    // 查詢商品
    @GetMapping("/products/{productId}")
    ResponseEntity<ProductEntity> getById(@PathVariable Integer productId){
        ProductEntity productEntity = productService.getById(productId);

        if(productEntity != null){
            return ResponseEntity.status(HttpStatus.OK).body(productEntity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 新增商品
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

    // 刪除商品
    @DeleteMapping("/products/{productId}")
    ResponseEntity<ProductEntity> deleteProduct(@PathVariable Integer productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 修改商品
    @PutMapping("/products/{productId}")
    ResponseEntity<ProductEntity> updateProductById(@PathVariable Integer productId, @RequestBody @Valid ProductDto productDto){

        ProductEntity productEntity = productService.getById(productId);

        if(productEntity != null){
            productService.updateProductById(productId, productDto);
            productEntity = productService.getById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(productEntity);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //註解掉，由另一個有查詢條件功能的取代
    //取得多筆商品
//    @GetMapping("/products")
//    ResponseEntity<List<ProductEntity>> getProducts(){
//        List<ProductEntity> productEntityList = productService.getProducts();
//
////        if(productEntityList.size() != 0){
////            return ResponseEntity.status(HttpStatus.OK).body(productEntityList);
////        }else{
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////        }
//        //查所有的 API，就算實際資料庫沒資料，也要返回 200
//        return ResponseEntity.status(HttpStatus.OK).body(productEntityList);
//    }

    //返回再多加一個總數(讓前端可算底下最後頁數)，因此返回類型修改，新增一個 Page類
    //取得商品，且提供自訂查詢條件，例如商品類型，與商品名稱的關鍵字
    //@Valid
    @Validated
    @GetMapping("/products")
    ResponseEntity<Page<ProductEntity>> getProductsBySearch(@RequestParam(required = false) ProductCategory category,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(defaultValue = "created_date") String order,
                                                            @RequestParam(defaultValue = "DESC") String orderType,
                                                            @RequestParam(defaultValue = "10") @Min(0) @Max(100) Integer limit,
                                                            @RequestParam(defaultValue = "0") @Min(0) Integer offset){

        //調用 service(包含後面的 dao) 的方法參數修改成一個類，避免後續查詢條件異動時，service 與 dao的 interface定義方法也要改，當然實作還是會動到
        ProductSearch productSearch = new ProductSearch();
        productSearch.setCategory(category);
        productSearch.setName(name);

        //排序
        productSearch.setOrder(order);
        productSearch.setOrderType(orderType);

        //分頁
        productSearch.setLimit(limit);
        productSearch.setOffset(offset);

        List<ProductEntity> productEntityList = productService.getProductsBySearch(productSearch);
        Integer count = productService.countProducts(productSearch);

        Page<ProductEntity> pageResultList =  new Page<ProductEntity> ();
        pageResultList.setLimit(limit.intValue());
        pageResultList.setOffset(offset.intValue());
        pageResultList.setCount(count);
        pageResultList.setResults(productEntityList);


        return ResponseEntity.status(HttpStatus.OK).body(pageResultList);
    }

//    //排序商品
//    @GetMapping("/products/order")
//    ResponseEntity<List<ProductEntity>> getProductsOrder( @RequestParam(required = false) String order){
//
//        List<ProductEntity> productEntityList = productService.getProductsOrder(order);
//
//        return ResponseEntity.status(HttpStatus.OK).body(productEntityList);
//    }


    }
