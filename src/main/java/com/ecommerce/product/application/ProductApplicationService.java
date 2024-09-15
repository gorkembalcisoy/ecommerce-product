package com.ecommerce.product.application;

import com.ecommerce.product.domain.event.ProductEventPublisher;
import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.rest.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductRepository productRepository;
    private final ProductEventPublisher productEventPublisher;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void createProduct(ProductCreateRequest productCreateRequest) throws DomainException {

        Product product = Product.builder()
                .description(productCreateRequest.getDescription())
                .name(productCreateRequest.getName())
                .price(productCreateRequest.getPrice())
                .stockQuantity(productCreateRequest.getStockQuantity())
                .build();

        product.isValid();
        product.setId(this.productRepository.save(product));
        this.productEventPublisher.publishProductCreatedEvent(product.getId().toString());
    }

    public void updateStockQuantity(String message) {
        String[] msgParts = message.split("_");
        Long productId = Long.parseLong(msgParts[0]);
        long orderId = Long.parseLong(msgParts[1]);
        int orderQuantity = Integer.parseInt(msgParts[2]);
        Product product = this.productRepository.findById(productId);
        if (!product.hasEnoughQuantity(orderQuantity)) {
            this.productEventPublisher.publishProductStockQuantityInsufficientEvent(Long.toString(orderId));
        } else {
            product.updateStockQuantity(orderQuantity);
            this.productRepository.update(product);
            this.productEventPublisher.publishProductStockDecreasedSuccessfullyEvent(Long.toString(orderId));
        }
    }
}
