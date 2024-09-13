package com.ecommerce.product.infrastructure.rest;

import com.ecommerce.product.application.ProductApplicationService;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.infrastructure.rest.exception.ResourceNotFoundException;
import com.ecommerce.product.infrastructure.rest.resource.ProductRestResource;
import com.ecommerce.product.infrastructure.rest.resource.ProductRestResourceAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProductRestResource>> getAllProducts() {
        return new ResponseEntity<>(createProductRestResources(), HttpStatus.OK);
    }

    private CollectionModel<ProductRestResource> createProductRestResources() {
        List<Product> products = productApplicationService.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return new ProductRestResourceAssembler().toCollectionModel(products);
    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Product getProductById(@PathVariable String id) {
//        return productApplicationService.getProductById(id);
//    }
//
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public Product addProduct(@RequestBody Product product) {
//        return productApplicationService.addProduct(product);
//    }
}
