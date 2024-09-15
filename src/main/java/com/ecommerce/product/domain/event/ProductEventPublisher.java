package com.ecommerce.product.domain.event;

public interface ProductEventPublisher {

    void publishProductCreatedEvent(String productId);

    void publishProductStockQuantityInsufficientEvent(String orderId);

    void publishProductStockDecreasedSuccessfullyEvent(String orderId);
}
