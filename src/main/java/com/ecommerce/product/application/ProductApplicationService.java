package com.ecommerce.product.application;

import com.ecommerce.product.common.DomainException;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.rest.command.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void createProduct(ProductRequest productRequest) throws DomainException {

        Product product = Product.builder()
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        product.isValid();
        product.setId(this.productRepository.save(product));
//        kafkaTemplate.send(topicName, product.getId().toString());
    }
}
