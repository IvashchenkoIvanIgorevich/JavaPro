package org.example.glovo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private int price;
    private int quantity;
    private Date creation;
    private Date modification;
}