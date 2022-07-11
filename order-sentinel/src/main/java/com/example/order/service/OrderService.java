package com.example.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @SentinelResource(value = "getOrder", blockHandler = "getOrderBlockHandler")
    public String getOrder(){
        return "查询订单信息";
    }

    public String getOrderBlockHandler(BlockException e) {
        return "被流控了";
    }
}
