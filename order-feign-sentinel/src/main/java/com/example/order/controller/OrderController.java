package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Qualifier("com.example.order.feign.StockFeignService")
    @Autowired
    StockFeignService stockFeignService;


    @RequestMapping("/add")
    public String add() {
        System.out.println("新增订单");

        String resp = stockFeignService.reduce2();

        return "新订单," + resp;
    }

    @RequestMapping("/get/{id}")
    @SentinelResource(value = "order-info", blockHandler = "getInfoBlockHandler")
    public String getById(@PathVariable("id") Integer id) {
        return "商品 - " + id;
    }

    public String getInfoBlockHandler(Integer id, BlockException e) {
        return "商品 - " + id + " 太火爆，被限流";
    }
}
