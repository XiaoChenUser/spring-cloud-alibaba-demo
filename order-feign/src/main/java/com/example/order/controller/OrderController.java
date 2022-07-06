package com.example.order.controller;

import com.example.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    StockFeignService stockFeignService;

    @RequestMapping("/add")
    public String add(){
        System.out.println("新增订单");

        String resp = stockFeignService.reduce();

        return "新订单," + resp;
    }
}
