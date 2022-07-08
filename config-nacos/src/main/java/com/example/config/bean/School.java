package com.example.config.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class School implements Serializable {
    private static final long serialVersionUID = -674846449020925438L;

    private String primary;
    private String high;
    private String college;
}
