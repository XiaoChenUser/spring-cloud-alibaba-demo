package com.example.controller;

import com.example.vo.ProductVO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductInfoController {
    @RequestMapping("/info/{id}")
    public ProductVO getInfo(@PathVariable Long id) {
        System.out.println("查询商品信息中...");
        return new ProductVO(id, "Apple", new BigDecimal("3.59"));
    }

    @GetMapping("/price")
    public Double getPrice(@RequestParam Long id) {
        ProductVO productVO = getInfo(id);
        System.out.println("价格是:" + productVO.getPrice().doubleValue() + "$");
        return productVO.getPrice().doubleValue();
    }
}
