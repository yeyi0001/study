package com.yeyi.study.activiti;

import org.springframework.stereotype.Component;

@Component
public class BpmnExpressionGenerator {

    public String assigneeExpression(String userTaskId) {
        return "${" + assigneeKey(userTaskId) + "}";
    }

    public String assigneeKey(String userTaskId) {
        return userTaskId + "_user";
    }

    public String flowConditionKey(String nodeId) {
        return nodeId + "_flow";
    }

    public String flowConditionVal(String flowId) {
        return flowId;
    }

    public String flowCondition(String nodeId, String flowId) {
        return "${"+flowConditionKey(nodeId)+"=='"+flowConditionVal(flowId)+"'}";
    }

}
