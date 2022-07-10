package com.example.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    public static final String HELLO_WORLD = "HelloWorld";

    @RequestMapping("/hello")
    public String hello() {
        Entry entry = null;
        try {
            entry = SphU.entry(HELLO_WORLD);
            System.out.println("hello world");
            return "hello";
        } catch (BlockException e) {
            e.printStackTrace();
            return "被流控了";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    @PostConstruct
    public void initRule() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(HELLO_WORLD);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
//        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
    }
}
