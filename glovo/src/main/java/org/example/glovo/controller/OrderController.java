package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.model.Order;
import org.example.glovo.model.Product;
import org.example.glovo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable String id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.add(order);
    }

    @PutMapping
    public Order update(@RequestBody Order order) {
        try {
            return orderService.update(order);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PatchMapping("/{id}/products")
    public Order addProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            return orderService.addProduct(id, product);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PatchMapping("/{orderId}/products/{productId}")
    public Order addProduct(@PathVariable String orderId, @PathVariable String productId) {
        try {
            return orderService.addProduct(orderId, productId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public boolean deleteProduct(@PathVariable String orderId, @PathVariable String productId) {
        try {
            return orderService.deleteProduct(orderId, productId);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{id}")
    public Order delete(@PathVariable String id) {
        return orderService.delete(id);
    }
}