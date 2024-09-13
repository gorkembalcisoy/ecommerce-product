package com.ecommerce.product.infrastructure.rest.command;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private double price;
    private int quantity;
}
