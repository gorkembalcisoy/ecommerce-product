package com.ecommerce.product.infrastructure;

import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.jpa.ProductEntityRepository;
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
        return productEntityRepository
                .findAll()
                .stream()
                .map(productEntity -> new Product(productEntity.getId(),
                        productEntity.getName(),
                        productEntity.getDescription(),
                        productEntity.getPrice(),
                        productEntity.getQuantity()))
                .collect(Collectors.toList());
    }
}
