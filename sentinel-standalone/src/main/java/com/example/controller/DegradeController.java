package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DegradeController {
    public static final String RESOURCE_NAME = "degrade";

    @RequestMapping("/degrade")
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "blockHandler")
    public String degrade(){
        throw new RuntimeException("内部错误");
    }

    public String blockHandler(BlockException e){
        e.printStackTrace();
        return "熔断降级：degrade for exception count";
    }

    @PostConstruct
    public void loadRules() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(RESOURCE_NAME);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rule.setCount(2);
        rule.setMinRequestAmount(5);
        rule.setStatIntervalMs(60 * 1000);
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
