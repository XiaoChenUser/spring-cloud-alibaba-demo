package com.example.order.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson2.JSON;
import com.example.order.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info(JSON.toJSONString(e.getRule()));
        Result result = null;
        if (e instanceof FlowException) {
            result = Result.error(401, "被流控了");
        } else if (e instanceof DegradeException) {
            result = Result.error(402, "被降级了");
        } else if (e instanceof ParamFlowException) {
            result = Result.error(403, "热点参数");
        } else if (e instanceof AuthorityException) {
            result = Result.error(405, "访问权限控制");
        } else if (e instanceof SystemBlockException) {
            result = Result.error(406, "系统自适应限流");
        } else {
            result = Result.error(407, "未知限流");
        }

        response.setStatus(500);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}
