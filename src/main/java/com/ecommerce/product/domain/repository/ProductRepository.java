package com.ecommerce.product.domain.repository;

import com.ecommerce.product.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Long save(Product product);

    Product findById(Long id);

    void update(Product product);
}
