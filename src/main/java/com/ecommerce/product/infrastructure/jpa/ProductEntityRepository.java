package com.ecommerce.product.infrastructure.jpa;

import com.ecommerce.product.infrastructure.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProductEntityRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findAll();
}
