package com.example.config.controller;

import com.example.config.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigCheckController {
    private final User user;

    @Autowired
    public ConfigCheckController(User user) {
        this.user = user;
    }


    @GetMapping("/config")
    public User getConfig(){
        return user;
    }
}
