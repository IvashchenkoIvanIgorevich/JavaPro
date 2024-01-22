package org.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {

    private String id;
    private List<Product> products;
    private int totalPrice;
    private int quantity;
    private Date creation;
    private Date modification;
}