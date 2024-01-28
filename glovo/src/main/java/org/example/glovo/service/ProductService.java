package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.mapper.ProductMapper;
import org.example.glovo.model.Product;
import org.example.glovo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductEntity add(Product product) {
        return productRepository.save(ProductMapper.toEntity(product));
    }

    public ProductEntity getById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
}