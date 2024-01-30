package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.entity.OrderEntity;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.exception.OrderNotFoundException;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.model.Order;
import org.example.glovo.model.Product;
import org.example.glovo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;

    public List<Order> getAll() {
        Iterable<OrderEntity> allOrdersEntity = orderRepository.findAll();

        List<Order> allOrders = new ArrayList<>();
        for (OrderEntity orderEntity : allOrdersEntity) {
            allOrders.add(OrderMapper.toModel(orderEntity));
        }

        return allOrders;
    }

    public Order add(Order order) {
        OrderEntity newOrderEntity = OrderMapper.toEntity(order);

        OrderEntity savedOrderEntity = orderRepository.save(newOrderEntity);

        return OrderMapper.toModel(savedOrderEntity);
    }

    public Order update(Order order) throws OrderNotFoundException {
        if (order.getId() == null) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        orderRepository.findById(order.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        OrderEntity updateOrderEntity = orderRepository.save(OrderMapper.toEntity(order));

        return OrderMapper.toModel(updateOrderEntity);
    }

    public Order getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order not found");
        }

        OrderEntity foundOrder = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return OrderMapper.toModel(foundOrder);
    }

    public Order addProduct(Long id, Product product) throws OrderNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        OrderEntity foundOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        ProductEntity addedProductEntity = productService.add(product);
        foundOrder.getProducts().add(addedProductEntity);

        OrderEntity updateOrderEntity = orderRepository.save(foundOrder);

        return OrderMapper.toModel(updateOrderEntity);
    }

    public Order addProduct(Long orderId, Long productId) throws OrderNotFoundException, ProductNotFoundException {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        OrderEntity foundOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        ProductEntity existingProductEntity = productService.getById(productId);
        foundOrder.getProducts().add(existingProductEntity);

        OrderEntity updatedOrderEntity = orderRepository.save(foundOrder);

        return OrderMapper.toModel(updatedOrderEntity);
    }

    public boolean deleteProduct(Long orderId, Long productId) throws OrderNotFoundException, ProductNotFoundException {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        OrderEntity foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));

        ProductEntity existingProductEntity = productService.getById(productId);
        foundOrder.getProducts().remove(existingProductEntity);

        orderRepository.save(foundOrder);

        return true;
    }

    public Order delete(Long id) throws OrderNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("Order id is a required argument");
        }

        OrderEntity foundOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.delete(foundOrder);

        return OrderMapper.toModel(foundOrder);
    }
}