package com.ecommerce.product.infrastructure.rest.resource;

import com.ecommerce.product.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRestResource extends RepresentationModel<ProductRestResource> {

    private String name;
    private String description;
    private double price;
    private int quantity;
}
