package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.exception.OrderNotFoundException;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.model.Order;
import org.example.glovo.model.Product;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private List<Order> orders;
    private ProductService productService;

    public List<Order> getAll() {
        return orders;
    }

    public Order add(Order order) {
        order.setId(Integer.toString(orders.size()));
        orders.add(order);
        return order;
    }

    public Order update(Order order) throws OrderNotFoundException {
        if (order.getId() == null || order.getId().isEmpty() || order.getId().isBlank()) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        Order currentOrder = orders.get(Integer.parseInt(order.getId()));
        if (currentOrder == null) {
            throw new OrderNotFoundException("Order not found");
        }

        currentOrder.setQuantity(order.getQuantity());
        currentOrder.setModification(new Date());
        currentOrder.setTotalPrice(order.getTotalPrice());
        currentOrder.setProducts(order.getProducts());

        return currentOrder;
    }

    public Order getById(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new IllegalArgumentException("Order not found");
        }

        return orders.get(Integer.parseInt(id));
    }

    public Order addProduct(String id, Product product) throws OrderNotFoundException {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        Order currentOrder = orders.get(Integer.parseInt(id));
        if (currentOrder == null) {
            throw new OrderNotFoundException("Order not found");
        }

        Product createdProduct = productService.add(product);
        currentOrder.getProducts().add(createdProduct);

        return currentOrder;
    }

    public Order addProduct(String orderId, String productId) throws OrderNotFoundException, ProductNotFoundException {
        if (orderId == null || orderId.isEmpty() || orderId.isBlank()) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        Order currentOrder = orders.get(Integer.parseInt(orderId));
        if (currentOrder == null) {
            throw new OrderNotFoundException("Order not found");
        }

        Product existingProduct = productService.getById(productId);
        currentOrder.getProducts().add(existingProduct);

        return currentOrder;
    }

    public boolean deleteProduct(String orderId, String productId) throws OrderNotFoundException, ProductNotFoundException {
        if (orderId == null || orderId.isEmpty() || orderId.isBlank()) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        Order currentOrder = orders.get(Integer.parseInt(orderId));
        if (currentOrder == null) {
            throw new OrderNotFoundException("Order not found");
        }

        Product existingProduct = productService.getById(productId);

        return currentOrder.getProducts().remove(existingProduct);
    }

    public Order delete(String id) {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        return orders.remove(Integer.parseInt(id));
    }
}