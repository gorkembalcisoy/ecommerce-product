package com.ecommerce.product.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEventKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.product-created-topic}")
    private String productCreatedTopic;

    @Value("${spring.kafka.producer.stock-insufficient-topic}")
    private String stockInsufficientTopic;

    @Value("${spring.kafka.producer.stock-decreased-topic}")
    private String stockDecreasedTopic;

    public void sendProductCreatedEvent(String productId) {
        this.kafkaTemplate.send(productCreatedTopic, productId);
    }

    public void sendProductStockQuantityInsufficientEvent(String orderId) {
        this.kafkaTemplate.send(stockInsufficientTopic, orderId);
    }

    public void sendProductStockDecreasedSuccessfullyEvent(String orderId) {
        this.kafkaTemplate.send(stockDecreasedTopic, orderId);
    }
}
