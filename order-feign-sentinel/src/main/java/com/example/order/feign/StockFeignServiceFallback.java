package com.example.order.feign;


import org.springframework.stereotype.Component;

@Component
public class StockFeignServiceFallback implements StockFeignService{
    @Override
    public String reduce2() {
        return "被熔断降级了";
    }
}
