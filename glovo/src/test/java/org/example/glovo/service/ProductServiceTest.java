package org.example.glovo.service;

import org.example.glovo.entity.ProductEntity;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.mapper.ProductMapper;
import org.example.glovo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    public ProductService productService;

    @Mock
    public ProductRepository productRepository;

    @Test
    void testAddProduct_ShouldReturnSavedProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setQuantity(2);
        productEntity.setPrice(100);
        productEntity.setName("product");
        when(productRepository.save(any())).thenReturn(productEntity);

        ProductEntity savedProduct = productService.add(ProductMapper.toModel(productEntity));

        assertEquals(productEntity, savedProduct);
    }

    @Test
    void testGetById_ShouldThrowProductNotFoundException_WhenProductCanNotBeFound() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getById(anyLong()));
    }

    @Test
    void testGetById_ShouldReturnFoundProduct() throws ProductNotFoundException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setQuantity(2);
        productEntity.setPrice(100);
        productEntity.setName("product");
        when(productRepository.findById(any())).thenReturn(Optional.of(productEntity));

        ProductEntity foundProduct = productService.getById(0L);

        assertEquals(productEntity, foundProduct);
    }
}