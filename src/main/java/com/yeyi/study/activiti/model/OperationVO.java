package com.yeyi.study.activiti.model;

import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;

public class OperationVO {
    private SequenceFlow flow;

    private FlowNode node;

    public FlowNode getNode() {
        return node;
    }

    public void setNode(FlowNode node) {
        this.node = node;
    }

    public SequenceFlow getFlow() {
        return flow;
    }

    public void setFlow(SequenceFlow flow) {
        this.flow = flow;
    }

}
