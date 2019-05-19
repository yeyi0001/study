package com.yeyi.study.service.impl;

import com.yeyi.study.service.FlowService;
import org.springframework.stereotype.Service;

@Service("flowService")
public class FlowServiceImpl implements FlowService{

    @Override
    public void start() {
        System.out.println("流程开始");
    }

}
