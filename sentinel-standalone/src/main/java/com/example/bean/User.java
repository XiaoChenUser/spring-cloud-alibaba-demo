package com.example.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}
