package com.ecommerce.product.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO create db index
    @Setter
    @Column(nullable = false)
    private String name;

    // TODO create db index
    @Setter
    @Column(nullable = false, length = 1000)
    private String description;

    @Setter
    @Column(nullable = false)
    private double price;

    @Setter
    @Column(nullable = false)
    private int stockQuantity;
}
