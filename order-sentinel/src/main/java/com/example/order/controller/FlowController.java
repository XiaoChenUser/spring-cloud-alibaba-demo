package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sentinel")
public class FlowController {
    @RequestMapping("/flow")
    @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flowCtrl() {
        return "每秒访问n次";
    }

    public String flowBlockHandler(BlockException e){
        return "流量控制";
    }

    @RequestMapping("/flow/thread")
    @SentinelResource(value = "flowThread", blockHandler = "flowBlockHandler")
    public String flowThread() throws InterruptedException {
        Thread.sleep(5000);
        return "每次n个线程访问";
    }


}
