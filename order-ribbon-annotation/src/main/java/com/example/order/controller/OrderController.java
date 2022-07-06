package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/add")
    public String add(){
        System.out.println("新增订单");
        String resp = restTemplate.getForObject("http://stock-service/stock/reduce", String.class);
        return "下单成功," + resp;
    }
}
