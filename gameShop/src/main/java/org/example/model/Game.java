package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Game {
    private int id;
    private String name;
    private String description;
    private int rating;
    private int cost;
    private Date release;
}
