package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public static final String USER_RESOURCE = "user";


    @GetMapping("/get")
    @SentinelResource(value = USER_RESOURCE, blockHandler = "blockHandlerForGetUser", fallback = "fallbackForGetUser")
    public User get(@RequestParam Integer id) {
        return new User(id,"Xiao Wang");
    }

    public User blockHandlerForGetUser(Integer id, BlockException e) {
        e.printStackTrace();
        System.out.println("被流控了");
        return new User(id, "系统繁忙");
    }

    public User fallbackForGetUser(Integer id, Throwable ex) {
        ex.printStackTrace();
        System.out.println("Internal error");
        return new User(id, "系统异常");
    }

    @PostConstruct
    public void loadRules(){
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(USER_RESOURCE);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
//        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
    }
}
