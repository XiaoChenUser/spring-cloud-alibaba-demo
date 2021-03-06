package com.example.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Value("${server.port}")
    private String port;


    @RequestMapping("/reduce")
    public String reduce(){
        System.out.println("扣减库存");
        return "[" + port + "] - 库存-1";
    }

    @RequestMapping("/reduce2")
    public String reduce2(){
        System.out.println("扣减库存");
        throw new RuntimeException("库存不足");
    }
}
