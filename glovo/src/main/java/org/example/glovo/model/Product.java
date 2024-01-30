package org.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private int price;
    private int quantity;
    private Date creation;
    private Date modification;
}