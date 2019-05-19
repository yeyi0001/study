package com.yeyi.study.activiti;

import com.yeyi.study.activiti.model.OperationVO;
import com.yeyi.study.service.FlowService;
import com.yeyi.study.service.ReportFlowService;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportTest {
    @Autowired
    private FlowService flowService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private ReportFlowService service;

    @Test
    public void getProcess() {
        Process process = service.getProcess();
        List<SequenceFlow> flows = process.findFlowElementsOfType(SequenceFlow.class);
        for (SequenceFlow flow : flows) {
            if (flow.getConditionExpression() != null) {
                System.out.println(flow.getConditionExpression());
            }
        }
    }

    @Test
    public void start() {
        service.start("usertask1_3");
    }

    @Test
    public void tasks() {
        for (Task task : service.tasks("usertask1_3")) {
            System.out.println(task);
        }
    }

    @Test
    public void operations() {
        List<OperationVO> operations = service.operations("92503");
        for (OperationVO operation : operations) {
            System.out.println(operation.getFlow());
            System.out.println(operation.getNode());
            System.out.println(operation.getNode().getName() + ":::" + operation.getFlow().getId());
        }
    }

    @Test
    public void complete() {
        service.complete("90004", "flow5", "lingdao");
    }


}
