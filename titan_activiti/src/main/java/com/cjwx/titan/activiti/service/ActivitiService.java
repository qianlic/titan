package com.cjwx.titan.activiti.service;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年04月27日  16:46
 */
public interface ActivitiService {

    ProcessInstance startProcessInstance(String key, Map<String, Object> variable);

    List<ProcessDefinition> getProcessDifinition(String key, Integer offset, Integer limit);

    void deleteProcessDifinition(String id);

    List<Task> getTask(String name, Integer offset, Integer limit);

    void setTaskAssignee(String id, String assignee);

    void setTaskVariable(String id, String name, Object o);

    Object getTaskVariable(String id, String name);

    void completeTask(String id);

    List<HistoricVariableInstance> getHistoricVariable(String name, Integer offset, Integer limit);

    List<HistoricProcessInstance> getHistoricProcessInstance(String key, Integer offset, Integer limit);

    List<HistoricActivityInstance> getHistoricActivityInstance(String id, Integer offset, Integer limit);

    List<HistoricTaskInstance> getHistoricTaskInstance(String id, Integer offset, Integer limit);

    void deploy(String name);

}
