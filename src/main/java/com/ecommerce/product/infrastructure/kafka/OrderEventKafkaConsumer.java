package com.ecommerce.product.infrastructure.kafka;

import com.ecommerce.product.application.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventKafkaConsumer {

    private final ProductApplicationService productApplicationService;

    @KafkaListener(topics = "${spring.kafka.consumer.order-created-topic}", groupId = "${spring.kafka.group-id}")
    public void listen(String message) {
        this.productApplicationService.updateStockQuantity(message);
    }
}
