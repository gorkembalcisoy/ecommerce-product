package com.ecommerce.product.infrastructure.rest;

import lombok.Data;

@Data
public class ProductCreateRequest {

    private String name;
    private String description;
    private double price;
    private int stockQuantity;
}
