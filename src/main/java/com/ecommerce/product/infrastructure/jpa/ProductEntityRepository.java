package com.ecommerce.product.infrastructure.jpa;

import com.ecommerce.product.infrastructure.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findAll();
}
