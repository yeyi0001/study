package com.yeyi.study.service;

import com.yeyi.study.activiti.model.OperationVO;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

public interface ReportFlowService {
    ProcessInstance start(String userId);

    List<Task> tasks(String userId);

    List<OperationVO> operations(String taskId);

    void complete(String taskId, String flowId, String userId);

    Process getProcess();
}
