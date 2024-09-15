package com.ecommerce.product.infrastructure.rest;

import com.ecommerce.product.application.ProductApplicationService;
import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = @Content),
            @ApiResponse(responseCode = "500", description = "Product not saved", content = @Content)
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductCreateRequest productCreateRequest) throws DomainException {
        this.productApplicationService.createProduct(productCreateRequest);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "500", description = "Product not found", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductRestResource>> getAllProducts() {
        return new ResponseEntity<>(createProductRestResources(), HttpStatus.OK);
    }

    private List<ProductRestResource> createProductRestResources() {
        List<Product> products = this.productApplicationService.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        ProductRestResourceTransformer productRestResourceTransformer = new ProductRestResourceTransformer();
        List<ProductRestResource> productRestResources = new ArrayList<>();
        products.forEach(product -> {
            ProductRestResource resource = productRestResourceTransformer.toRestResource(product);
            productRestResources.add(resource);
        });
        return productRestResources;
    }
}
