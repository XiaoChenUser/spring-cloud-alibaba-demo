package com.example.order.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private Long id;
    private String name;
    private BigDecimal price;
}
