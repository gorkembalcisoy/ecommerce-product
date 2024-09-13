package com.ecommerce.product.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "PRODUCT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 255, message = "Name must contain minimum 3 characters and maximum 255 characters")
    private String name;

    @Column(nullable = false, length = 1000)
    @Size(min = 10, max = 1000, message = "Description must contain minimum 10 characters and maximum 1000 characters")
    private String description;

    @Column(nullable = false)
    @Positive(message = "Price must be greater than zero")
    private double price;

    @Column(nullable = false)
    @Positive(message = "Quantity must be greater than zero")
    private int quantity;
}
