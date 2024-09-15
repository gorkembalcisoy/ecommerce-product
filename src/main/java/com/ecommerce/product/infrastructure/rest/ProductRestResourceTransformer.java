package com.ecommerce.product.infrastructure.rest;

import com.ecommerce.product.domain.model.Product;

public class ProductRestResourceTransformer {

    public ProductRestResource toRestResource(Product product) {
        ProductRestResource productRestResource = new ProductRestResource();
        productRestResource.setId(product.getId());
        productRestResource.setDescription(product.getDescription());
        productRestResource.setName(product.getName());
        productRestResource.setPrice(product.getPrice());
        productRestResource.setStockQuantity(product.getStockQuantity());
        return productRestResource;
    }
}
