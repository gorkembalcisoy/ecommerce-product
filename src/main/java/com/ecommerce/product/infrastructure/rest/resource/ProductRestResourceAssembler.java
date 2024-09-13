package com.ecommerce.product.infrastructure.rest.resource;

import com.ecommerce.product.domain.model.Product;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class ProductRestResourceAssembler implements RepresentationModelAssembler<Product, ProductRestResource> {

    @Override
    public ProductRestResource toModel(Product product) {
        ProductRestResource productRestResource = new ProductRestResource();
        productRestResource.setDescription(product.getDescription());
        productRestResource.setName(product.getName());
        productRestResource.setPrice(product.getPrice());
        productRestResource.setQuantity(product.getQuantity());
        return productRestResource;
    }
}
