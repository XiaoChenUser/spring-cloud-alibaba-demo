package com.example.order.controller;

import com.example.order.bean.ProductVO;
import com.example.order.feign.ProductInfoFeignService;
import com.example.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    StockFeignService stockFeignService;
    @Autowired
    ProductInfoFeignService productInfoFeignService;


    @RequestMapping("/add")
    public String add() {
        System.out.println("新增订单");

        String resp = stockFeignService.reduce();

        return "新订单," + resp;
    }

    @GetMapping("/search")
    public ProductVO getProductInfo(@RequestParam Long id) {
        return productInfoFeignService.getInfo(id);
    }
}
