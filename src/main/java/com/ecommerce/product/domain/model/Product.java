package com.ecommerce.product.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
