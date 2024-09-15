package com.ecommerce.product.infrastructure.jpa;

import com.ecommerce.product.infrastructure.jpa.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProductJpaEntityRepository extends JpaRepository<ProductJpaEntity, Long> {

    List<ProductJpaEntity> findAll();
}
