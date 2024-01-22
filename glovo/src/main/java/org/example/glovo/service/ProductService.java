package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private List<Product> products;

    public Product add(Product product) {
        product.setId(Integer.toString(products.size()));
        products.add(product);
        return product;
    }

    public Product getById(String id) throws ProductNotFoundException {
        Product product = products.get(Integer.parseInt(id));
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        return product;
    }
}