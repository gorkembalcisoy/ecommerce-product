package com.ecommerce.product.infrastructure.rest;

import com.ecommerce.product.application.ProductApplicationService;
import com.ecommerce.product.common.DomainException;
import com.ecommerce.product.domain.model.Product;
import com.ecommerce.product.infrastructure.rest.command.ProductRequest;
import com.ecommerce.product.infrastructure.rest.exception.ResourceNotFoundException;
import com.ecommerce.product.infrastructure.rest.resource.ProductRestResource;
import com.ecommerce.product.infrastructure.rest.resource.ProductRestResourceAssembler;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void createProduct(@RequestBody ProductRequest productRequest) throws DomainException {
        this.productApplicationService.createProduct(productRequest);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CollectionModel.class))}),
            @ApiResponse(responseCode = "500", description = "Product not found", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProductRestResource>> getAllProducts() {
        return new ResponseEntity<>(createProductRestResources(), HttpStatus.OK);
    }

    private CollectionModel<ProductRestResource> createProductRestResources() {
        List<Product> products = this.productApplicationService.findAll();
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
}
