package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private int id;
    private String nickname;
    private String password;
    private Date birthday;
    private int amount;
}
