package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class FlowController {
    @Autowired
    private OrderService orderService;


    @RequestMapping("/flow")
    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flowCtrl() {
        return "每秒访问n次";
    }

    public String flowBlockHandler(BlockException e) {
        return "流量控制";
    }

    @RequestMapping("/flow/thread")
    @SentinelResource(value = "flowThread", blockHandler = "flowBlockHandler")
    public String flowThread() throws InterruptedException {
        Thread.sleep(5000);
        return "每次n个线程访问";
    }

    @RequestMapping("/add")
    public String add() {
        return "新增订单";
    }

    @RequestMapping("/get")
    public String get() {
        return "查询订单";
    }

    @RequestMapping("/test1")
    public String test1() {
        return orderService.getOrder();
    }

    @RequestMapping("/test2")
    public String test2() {
        return orderService.getOrder();
    }

    @RequestMapping("/circuit/breaking1")
    public String slowRequestRatio() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "慢调用";
    }

    @RequestMapping("/circuit/breaking2")
    public String errorRatio() {
        throw new RuntimeException("模拟异常");
    }
}
