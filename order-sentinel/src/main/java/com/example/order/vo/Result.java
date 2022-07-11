package com.example.order.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;


    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static Result error(int code, String msg) {
        return new Result(code,msg);
    }
}
