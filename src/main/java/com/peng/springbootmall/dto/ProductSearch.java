package com.peng.springbootmall.dto;

import com.peng.springbootmall.constant.ProductCategory;

public class ProductSearch {
    ProductCategory category;
    String name;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
