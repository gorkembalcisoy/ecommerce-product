package com.ecommerce.product.infrastructure;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.jpa.ProductJpaEntityRepository;
import com.ecommerce.product.infrastructure.jpa.entity.ProductJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaEntityRepository productJpaEntityRepository;

    @Override
    public List<Product> findAll() {

        return this.productJpaEntityRepository
                .findAll()
                .stream()
                .map(productJpaEntity -> new Product(productJpaEntity.getId(),
                        productJpaEntity.getName(),
                        productJpaEntity.getDescription(),
                        productJpaEntity.getPrice(),
                        productJpaEntity.getStockQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Product product) {

        ProductJpaEntity productJpaEntity = ProductJpaEntity.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
        this.productJpaEntityRepository.save(productJpaEntity);
        return productJpaEntity.getId();
    }
}
