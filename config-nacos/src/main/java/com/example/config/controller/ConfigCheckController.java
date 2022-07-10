package com.example.config.controller;

import com.example.config.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigCheckController {
    private final User user;
    @Value("${extra}")
    private String extraInfo;

    @Autowired
    public ConfigCheckController(User user) {
        this.user = user;
    }


    @GetMapping("/config")
    public User getConfig(){
        System.out.println(user.toString());
        System.out.println("extra:" + extraInfo);
        return user;
    }
}
