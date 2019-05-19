package com.yeyi.study.activiti;

import com.yeyi.study.service.FlowService;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.validation.validator.impl.SequenceflowValidator;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest {

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


    @Test
    public void TestStartProcess() {
        System.out.println("Start.........");
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess_1", "2");
        System.out.println("流程启动成功，流程id:" + pi.getId());
    }


    public void getProcessDef(String defId) {
        BpmnModel bpmnModel = getBpmnModel(defId);
        Process process = bpmnModel.getProcessById("myProcess_1");
        UserTask userTask = (UserTask) process.getFlowElement("_4");
        System.out.println(userTask.getAssignee());
        System.out.println(process.getFlowElements().size());
    }

    @Test
    public void completeWithLocalVar() {
        String userId = "yeyi";
        List<Task> resultTask = taskService.createTaskQuery().taskAssignee(userId).list();
        for (Task task : resultTask) {
            taskService.setVariable(task.getId(), "user", "yeyi2");
            taskService.setVariable(task.getId(), "array", Arrays.asList(1, 2, 3, 4, 0));
            System.out.println("task" + task.getName());
        }
    }

    @Test
    public void complete() {
        String taskId = "35014";
        taskService.setVariable(taskId, "user", "0518");
        taskService.complete(taskId);
    }

    @Test
    public void img() throws IOException {
        BpmnModel bpmnModel = getBpmnModel("myProcess_1:8:42504");
        String font = "宋体";
        ProcessDiagramGenerator processDiagramGenerator = new ShareniuProcessDiagramGeneratorExt();

        String imageType = "png";
        InputStream inputStream = processDiagramGenerator.generateDiagram(bpmnModel, imageType, Collections.emptyList(), Collections.emptyList(), font, font, font, null, 1.0);

        File file = FileUtils.getFile("img", "proc." + imageType);

        System.out.println(file.getAbsoluteFile());

        FileUtils.copyInputStreamToFile(inputStream, file);
    }

    private BpmnModel getBpmnModel(String s) {
        return repositoryService.getBpmnModel(s);
    }

    @Test
    public void bpmnToXml() throws IOException {
        ProcessDefinition report = repositoryService.createProcessDefinitionQuery().processDefinitionKey("report").latestVersion().singleResult();
        BpmnModel bpmnModel = getBpmnModel(report.getId());

        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

        Process process = bpmnModel.getProcesses().get(0);

        List<UserTask> userTasks = process.findFlowElementsOfType(UserTask.class);

        Function<String, String> assignee = userTaskId -> "${" + userTaskId + "-user}";

        Function<String, String> flowConditionKey = userTaskId -> userTaskId + "-flow";

        BiFunction<String, String, String> flowCondition = (userTaskId, flowId) -> "${"+flowConditionKey.apply(userTaskId)+"='"+flowId+"'}";

        for (UserTask userTask : userTasks) {
            String userTaskId = userTask.getId();
            userTask.setAssignee(assignee.apply(userTaskId));

            if (userTask.getOutgoingFlows().size() > 1) {
                List<SequenceFlow> flows = findOutgoingFlows(userTask);
                for (SequenceFlow flow : flows) {
                    flow.setConditionExpression(flowCondition.apply(userTaskId, flow.getId()));
                }
            }
        }

        System.out.println(process);

        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);

        File xml = FileUtils.getFile("xml", "report.bpmn");

        FileUtils.writeByteArrayToFile(xml, convertToXML);

    }

    private List<SequenceFlow> findOutgoingFlows(FlowNode node) {
        List<SequenceFlow> list = new ArrayList<>();
        for (SequenceFlow flow : node.getOutgoingFlows()) {
            FlowElement target = flow.getTargetFlowElement();
            if (target instanceof UserTask) {
                list.add(flow);
            } else if (target instanceof ExclusiveGateway) {
                list.addAll(findOutgoingFlows((ExclusiveGateway) target));
            }
        }
        return list;
    }

    @Test
    public void findTasksByUserId() {
        String userId = "yeyi";
        List<Task> resultTask = taskService.createTaskQuery().taskAssignee(userId).list();
        for (Task task : resultTask) {
            getProcessDef(task.getProcessDefinitionId());
        }
        System.out.println("任务列表：" + resultTask);
    }

}
