package com.ecommerce.product.infrastructure;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.jpa.ProductEntityRepository;
import com.ecommerce.product.infrastructure.jpa.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductEntityRepository productEntityRepository;

    @Override
    public List<Product> findAll() {

        return this.productEntityRepository
                .findAll()
                .stream()
                .map(productEntity -> new Product(productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getDescription(),
                        productEntity.getPrice(),
                        productEntity.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Product product) {

        ProductEntity productEntity = ProductEntity.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
        this.productEntityRepository.save(productEntity);
        return productEntity.getId();
    }
}
