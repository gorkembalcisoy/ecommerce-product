package com.ecommerce.product.application;

import com.ecommerce.product.domain.event.ProductEventPublisher;
import com.ecommerce.product.domain.exception.DomainException;
import com.ecommerce.product.domain.repository.ProductRepository;
import com.ecommerce.product.infrastructure.rest.ProductCreateRequest;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ProductApplicationServiceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private ProductEventPublisher productEventPublisherMock;

    @Test
    public void validProductShouldBeCreatedSuccessfully() throws DomainException {
        ProductApplicationService productApplicationService = new ProductApplicationService(productRepositoryMock, productEventPublisherMock);
        ProductCreateRequest productCreateRequest = getProductCreateRequest(true);
        productApplicationService.createProduct(productCreateRequest);
        Mockito.verify(productEventPublisherMock, Mockito.times(1)).publishProductCreatedEvent(Mockito.anyString());
    }

    @Test(expected = DomainException.class)
    public void invalidProductShouldBeCreatedDomainException() throws DomainException {
        ProductApplicationService productApplicationService = new ProductApplicationService(productRepositoryMock, productEventPublisherMock);
        ProductCreateRequest productCreateRequest = getProductCreateRequest(false);
        productApplicationService.createProduct(productCreateRequest);
    }

    private static ProductCreateRequest getProductCreateRequest(boolean valid) {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setDescription(valid ? "valid" : "inv");
        productCreateRequest.setName("testname");
        productCreateRequest.setPrice(100);
        productCreateRequest.setStockQuantity(11);
        return productCreateRequest;
    }

}