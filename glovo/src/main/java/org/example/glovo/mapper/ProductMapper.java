package org.example.glovo.mapper;

import org.example.glovo.entity.ProductEntity;
import org.example.glovo.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toEntity(Product model) {
        ProductEntity entity = new ProductEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setPrice(model.getPrice());
        entity.setCreation(model.getCreation());
        entity.setModification(model.getModification());
        entity.setQuantity(model.getQuantity());
        return entity;
    }

    public static List<ProductEntity> toEntities(List<Product> models) {
        if (models == null) {
            return new ArrayList<>();
        }
        return models.stream().map(ProductMapper::toEntity).collect(Collectors.toList());
    }

    public static Product toModel(ProductEntity entity) {
        return Product
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .creation(entity.getCreation())
                .modification(entity.getModification()).build();
    }

    public static List<Product> toModels(List<ProductEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(ProductMapper::toModel).collect(Collectors.toList());
    }
}
