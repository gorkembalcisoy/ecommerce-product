package com.ecommerce.product.domain.model;

import com.ecommerce.product.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
@Builder
public class Product {

    @Setter
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public void isValid() throws DomainException {

        if (StringUtils.isAllEmpty(this.name) || (this.name.length() < 3 || this.name.length() > 255)) {
            throw new DomainException("A valid name value is required.");
        }
        if (StringUtils.isAllEmpty(this.description) || (this.description.length() < 5 || this.description.length() > 1000)) {
            throw new DomainException("A valid description value is required.");
        }
        if (this.price < 0) {
            throw new DomainException("Price must be greate than zero.");
        }
        if (this.stockQuantity < 0) {
            throw new DomainException("Stock quantity must be greate than zero.");
        }
    }

    public boolean hasEnoughQuantity(int orderQuantity) {
        return orderQuantity <= this.stockQuantity;
    }

    public void updateStockQuantity(int orderQuantity) {
        this.stockQuantity = this.stockQuantity - orderQuantity;
    }
}
