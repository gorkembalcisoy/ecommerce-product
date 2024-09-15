package com.ecommerce.product.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT", indexes = {@Index(name="product_name_indx", columnList = "name"), @Index(name="product_desc_indx", columnList = "description")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

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
