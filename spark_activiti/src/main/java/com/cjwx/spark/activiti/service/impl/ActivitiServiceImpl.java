package com.cjwx.spark.activiti.service.impl;

import com.cjwx.spark.activiti.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  16:47
 */
@Service("activityService")
public class ActivitiServiceImpl implements ActivitiService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Override
    public ProcessInstance startProcessInstance(String key, Map<String, Object> variable) {
        return runtimeService.startProcessInstanceByKey(key, variable);
    }

    @Override
    public List<ProcessDefinition> getProcessDifinition(String key, Integer offset, Integer limit) {
        return repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .listPage(offset, limit);
    }

    @Override
    public void deleteProcessDifinition(String id) {
        repositoryService.deleteDeployment(id, true);
    }

    @Override
    public List<Task> getTask(String name, Integer offset, Integer limit) {
        return taskService.createTaskQuery()
                .taskAssignee(name)
                .listPage(offset, limit);
    }

    @Override
    public void setTaskAssignee(String id, String assignee) {
        taskService.setAssignee(id, assignee);
    }

    @Override
    public void setTaskVariable(String id, String name, Object o) {
        taskService.setVariable(id, name, o);
    }

    @Override
    public Object getTaskVariable(String id, String name) {
        return taskService.getVariable(id, name);
    }

    @Override
    public void completeTask(String id) {
        taskService.complete(id);
    }

    @Override
    public List<HistoricVariableInstance> getHistoricVariable(String name, Integer offset, Integer limit) {
        return historyService.createHistoricVariableInstanceQuery()
                .variableName(name)
                .listPage(offset, limit);
    }

    @Override
    public List<HistoricProcessInstance> getHistoricProcessInstance(String key, Integer offset, Integer limit) {
        return historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(key)
                .listPage(offset, limit);
    }

    @Override
    public List<HistoricActivityInstance> getHistoricActivityInstance(String id, Integer offset, Integer limit) {
        return historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(id)
                .listPage(offset, limit);
    }

    @Override
    public List<HistoricTaskInstance> getHistoricTaskInstance(String id, Integer offset, Integer limit) {
        return historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(id)
                .listPage(offset, limit);
    }

    @Override
    public void deploy(String name) {
        repositoryService.createDeployment()
                .addClasspathResource(name)
                .deploy();
    }

}
