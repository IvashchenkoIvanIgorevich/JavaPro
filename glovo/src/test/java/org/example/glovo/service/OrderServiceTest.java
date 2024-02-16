package org.example.glovo.service;

import org.example.glovo.entity.OrderEntity;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.exception.OrderNotFoundException;
import org.example.glovo.exception.ProductNotFoundException;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.model.Order;
import org.example.glovo.model.Product;
import org.example.glovo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceTest {

    @InjectMocks
    public OrderService orderService;

    @Mock
    public OrderRepository orderRepository;

    @Mock
    public ProductService productService;

    @Test
    void testGetAll_ShouldReturnAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new OrderEntity()));

        List<Order> orders = orderService.getAll();

        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
    }

    @Test
    void testAdd_ShouldReturnNewCreatedOrder() {
        Order createdOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(List.of(new Product()))
                .build();
        when(orderRepository.save(any())).thenReturn(OrderMapper.toEntity(createdOrder));

        Order savedOrder = orderService.add(new Order());

        assertEquals(createdOrder, savedOrder);
    }

    @Test
    void testUpdate_ShouldThrowIllegalArgumentException_WhenOrderIdNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.update(new Order()));
    }

    @Test
    void testUpdate_ShouldThrowOrderNotFoundException_WhenOrderCanNotBeFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.update(Order.builder().id(anyLong()).build()));
    }

    @Test
    void testUpdate_ShouldReturnUpdatedOrder() throws OrderNotFoundException {
        Order inputOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(List.of(new Product()))
                .build();
        when(orderRepository.findById(any())).thenReturn(Optional.of(new OrderEntity()));
        when(orderRepository.save(any())).thenReturn(OrderMapper.toEntity(inputOrder));

        Order updatedOrder = orderService.update(inputOrder);

        assertEquals(inputOrder, updatedOrder);
    }

    @Test
    void testGetById_ShouldThrowIllegalArgumentException_WhenOrderIdNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.getById(null));
    }

    @Test
    void testGetById_ShouldThrowOrderNotFoundException_WhenOrderCanNotBeFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getById(anyLong()));
    }

    @Test
    void testGetById_ShouldReturnFoundOrder() throws OrderNotFoundException {
        Order existingOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(List.of(new Product()))
                .build();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderMapper.toEntity(existingOrder)));

        Order foundOrder = orderService.getById(anyLong());

        assertEquals(existingOrder, foundOrder);
    }

    @Test
    void testAddNewProduct_ShouldThrowIllegalArgumentException_WhenOrderIdNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.addProduct(null, new Product()));
    }

    @Test
    void testAddNewProduct_ShouldThrowOrderNotFoundException_WhenOrderCanNotBeFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.addProduct(anyLong(), new Product()));
    }

    @Test
    void testAddNewProduct_ShouldSavedNewProductAndAddToOrder() throws OrderNotFoundException {
        Order existingOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(new ArrayList<>())
                .build();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderMapper.toEntity(existingOrder)));
        when(productService.add(any())).thenReturn(new ProductEntity());
        when(orderRepository.save(any())).thenReturn(OrderMapper.toEntity(existingOrder));

        Order actualOrder = orderService.addProduct(anyLong(), new Product());

        assertEquals(existingOrder, actualOrder);
    }

    @Test
    void testDeleteProduct_ShouldThrowIllegalArgumentException_WhenOrderIdNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.deleteProduct(null, null));
    }

    @Test
    void testDeleteProduct_ShouldThrowOrderNotFoundException_WhenOrderCanNotBeFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteProduct(0L, 0L));
    }

    @Test
    void testDeleteProduct_ShouldReturnTrue_WhenProductWasDeleted() throws ProductNotFoundException, OrderNotFoundException {
        Order existingOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(new ArrayList<>())
                .build();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderMapper.toEntity(existingOrder)));
        when(productService.getById(anyLong())).thenReturn(new ProductEntity());
        when(orderRepository.save(any())).thenReturn(new OrderEntity());

        boolean result = orderService.deleteProduct(1L, 1L);

        assertTrue(result);
    }

    @Test
    void testDeleteOrder_ShouldThrowIllegalArgumentException_WhenOrderIdNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.delete(null));
    }

    @Test
    void testDeleteOrder_ShouldThrowOrderNotFoundException_WhenOrderCanNotBeFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.delete(0L));
    }

    @Test
    void testDeleteOrder_ShouldReturnDeletedOrder() throws OrderNotFoundException {
        Order existingOrder = Order.builder()
                .id(1L)
                .price(100)
                .quantity(1)
                .products(new ArrayList<>())
                .build();
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderMapper.toEntity(existingOrder)));

        orderService.delete(1L);

        verify(orderRepository, times(1)).delete(any(OrderEntity.class));
    }
}