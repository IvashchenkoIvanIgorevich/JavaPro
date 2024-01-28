package org.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Order {

    private Long id;
    private List<Product> products;
    private int price;
    private int quantity;
    private Date creation;
    private Date modification;
}