package com.example;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 纯代码方式使用 Sentinel。
 */
public class HelloWorld {
    public static final String RESOURCE_NAME = "Hello";

    public static void main(String[] args) {
        loadRules();
        while (true) {
            try (Entry entry = SphU.entry(RESOURCE_NAME)) {
                System.out.println("Hello world");
            } catch (BlockException exception) {
                System.out.println("被流控了");
                break;
            }
        }
    }

    public static void loadRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(RESOURCE_NAME);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
