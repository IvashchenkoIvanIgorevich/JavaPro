package org.example.glovo.mapper;

import org.example.glovo.entity.OrderEntity;
import org.example.glovo.model.Order;

public class OrderMapper {

    public static OrderEntity toEntity(Order model) {
        OrderEntity entity = new OrderEntity();
        entity.setId(model.getId());
        entity.setQuantity(model.getQuantity());
        entity.setPrice(model.getPrice());
        entity.setCreation(model.getCreation());
        entity.setModification(model.getModification());
        entity.setProducts(ProductMapper.toEntities(model.getProducts()));
        return entity;
    }

    public static Order toModel(OrderEntity entity) {
        return Order.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .creation(entity.getCreation())
                .modification(entity.getModification())
                .products(ProductMapper.toModels(entity.getProducts()))
                .build();
    }
}
