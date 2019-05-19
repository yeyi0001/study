package com.yeyi.study.service.impl;

import com.yeyi.study.activiti.BpmnExpressionGenerator;
import com.yeyi.study.activiti.model.OperationVO;
import com.yeyi.study.service.FlowService;
import com.yeyi.study.service.ReportFlowService;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支持遵循下列规则的流程图
 * 1. 一个文件中只有一个流程 (process 节点)
 * 2. 一个流程只有一个开始节点
 * 3. 一个开始节点只有一条出线
 * 4. task 为 UserTask
 * 5. 网关为“排他网关”
 */
@Service
public class ReportFlowServiceImpl implements ReportFlowService {
    @Autowired
    private FlowService flowService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    private BpmnExpressionGenerator expressionGenerator;

    private static final String DEFINITION_KEY = "report";

    @Override
    public ProcessInstance start(String userId) {

        Process process = getProcess();

        StartEvent startEvent = process.findFlowElementsOfType(StartEvent.class).get(0);

        String userTaskId = startEvent.getOutgoingFlows().get(0).getTargetFlowElement().getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put(expressionGenerator.assigneeKey(userTaskId), userId);
        return runtimeService.startProcessInstanceByKey(DEFINITION_KEY, variables);
    }

    @Override
    public List<Task> tasks(String userId) {
        return taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
    }

    @Override
    public List<OperationVO> operations(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        UserTask userTask = (UserTask) getProcess().getFlowElement(task.getTaskDefinitionKey());

        List<SequenceFlow> flows = findOutgoingFlows(userTask);

        List<OperationVO> vos = new ArrayList<>();

        for (SequenceFlow flow : flows) {
            OperationVO vo = new OperationVO();
            vo.setFlow(flow);
            vo.setNode((FlowNode) flow.getTargetFlowElement());
            vos.add(vo);
        }

        return vos;
    }

    @Override
    public void complete(String taskId, String flowId, String userId) {
        // 设置决定流向和下一步办理人的流程变量
        Map<String, Object> variables = new HashMap<>();

        SequenceFlow flow = (SequenceFlow) getProcess().getFlowElement(flowId);

        // 办理人
        String userTaskId = flow.getTargetFlowElement().getId();
        variables.put(expressionGenerator.assigneeKey(userTaskId), userId);

        setFlowConditionVar(flow, variables);

        // 完成任务
        taskService.complete(taskId, variables);
    }

    private void setFlowConditionVar(SequenceFlow flow, Map<String, Object> variables) {
        FlowNode sourceNode = (FlowNode) flow.getSourceFlowElement();

        if (sourceNode.getOutgoingFlows().size() > 1) {
            variables.put(expressionGenerator.flowConditionKey(sourceNode.getId()), expressionGenerator.flowConditionVal(flow.getId()));
        }

        if (sourceNode instanceof ExclusiveGateway) {
            setFlowConditionVar(sourceNode.getIncomingFlows().get(0), variables);
        }
    }
    
    private List<SequenceFlow> findOutgoingFlows(FlowNode node) {
        List<SequenceFlow> list = new ArrayList<>();
        for (SequenceFlow flow : node.getOutgoingFlows()) {
            FlowElement target = flow.getTargetFlowElement();
            if (target instanceof UserTask || target instanceof EndEvent) {
                list.add(flow);
            } else if (target instanceof ExclusiveGateway) {
                list.addAll(findOutgoingFlows((ExclusiveGateway) target));
            }
        }
        return list;
    }

    @Override
    public Process getProcess() {
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(DEFINITION_KEY).latestVersion().singleResult();
        return repositoryService.getBpmnModel(definition.getId()).getProcesses().get(0);
    }

}
