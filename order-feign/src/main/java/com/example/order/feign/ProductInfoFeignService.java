package com.example.order.feign;

import com.example.order.bean.ProductVO;
import com.example.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "product-service", path = "/product", configuration = FeignConfig.class)
@FeignClient(name = "product-service", path = "/product")
public interface ProductInfoFeignService {
    @RequestMapping("/info/{id}")
    ProductVO getInfo(@PathVariable("id") Long id);

    @GetMapping("/price")
    Double getPrice(@RequestParam("id") Long id);
}
