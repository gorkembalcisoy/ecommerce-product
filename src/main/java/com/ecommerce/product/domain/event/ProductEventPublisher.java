package com.ecommerce.product.domain.event;

public interface ProductEventPublisher {

    void publishProductCreatedEvent(String productId);
}
