package com.ecommerce.product.infrastructure;

import com.ecommerce.product.domain.event.ProductEventPublisher;
import com.ecommerce.product.infrastructure.kafka.ProductEventKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventPublisherImpl implements ProductEventPublisher {

    private final ProductEventKafkaProducer productEventKafkaProducer;

    @Override
    public void publishProductCreatedEvent(String productId) {
        this.productEventKafkaProducer.sendProductCreatedEvent(productId);
    }

    @Override
    public void publishProductStockQuantityInsufficientEvent(String orderId) {
        this.productEventKafkaProducer.sendProductStockQuantityInsufficientEvent(orderId);
    }

    @Override
    public void publishProductStockDecreasedSuccessfullyEvent(String orderId) {
        this.productEventKafkaProducer.sendProductStockDecreasedSuccessfullyEvent(orderId);
    }
}
