package com.yym.flowable.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yym.flowable.model.ApproveRejectVO;
import com.yym.flowable.model.AskForLeaveVO;
import com.yym.flowable.model.HistoryInfo;
import com.yym.flowable.model.RespBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 请假流程具体实现
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 14:43
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AskForLeaveService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryService historyService;

    @Transactional
    public RespBean askForLeave(AskForLeaveVO askForLeaveVO) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", askForLeaveVO.getName());
        variables.put("days", askForLeaveVO.getDays());
        variables.put("reason", askForLeaveVO.getReason());
        variables.put("approveType", askForLeaveVO.getApproveType());
        variables.put("approveUser", askForLeaveVO.getApproveUser());
        variables.put("approveRole", askForLeaveVO.getApproveRole());
        try {
            //  流程引擎名称  流程key  变量
            runtimeService.startProcessInstanceByKey("holidayRequest", askForLeaveVO.getName(), variables);
            return RespBean.ok("已提交请假申请");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("提交申请失败");
    }

    public RespBean leaveList() {
        // 从security上下文中获取当前用户名字
        String identity = SecurityContextHolder.getContext().getAuthentication().getName();
        // 找到分配给我的任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(identity).list();
        // 找到所有分配给我的角色任务
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        authorities.forEach(authority -> tasks.addAll(taskService.createTaskQuery().taskCandidateGroup(authority.getAuthority()).list()));

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        tasks.forEach(task -> {
            Map<String, Object> variables = taskService.getVariables(task.getId());
            variables.put("id", task.getId());
            list.add(variables);
        });
        return RespBean.ok("加载成功", list);
    }

    public RespBean askForLeaveHandler(ApproveRejectVO approveRejectVO) {

        try {
            Boolean approve = approveRejectVO.getApprove();
            HashMap<String, Object> variables = new HashMap<>();
            variables.put("approved", approve);
            variables.put("employee", approveRejectVO.getName());
            // 查出对应的task
            Task task = taskService.createTaskQuery().taskId(approveRejectVO.getTaskId()).singleResult();
            // 传入对应的参数后, 继续向下走
            taskService.complete(task.getId(), variables);
            if (approve) {
                // 如果同意, 继续向前走一步
                Task nextTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                taskService.complete(nextTask.getId());
            }
            return RespBean.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("操作失败");
    }

    public RespBean searchResult() {
        // 从security的上下文中获取当前登录用户的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ArrayList<HistoryInfo> historyInfos = new ArrayList<>();
        // 根据开始传入的参数key, 查询执行的流程
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(name)
                .orderByProcessInstanceStartTime()
                .desc().list();

        // 遍历每一个流程的详细信息, historicProcessInstances查询历史流程
        historicProcessInstances.forEach(historicProcessInstance -> {

            HistoryInfo historyInfo = new HistoryInfo();
            historyInfo.setStatus(3);
            Date startTime = historicProcessInstance.getStartTime();
            Date endTime = historicProcessInstance.getEndTime();
            // 查询流程变量
            List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId())
                    .list();
            log.info("historicVariableInstances: {}", historicVariableInstances);
            historicVariableInstances.forEach(historicVariableInstance -> {
                populateHistoryInfo(historyInfos, historyInfo, startTime, endTime, historicVariableInstance);
            });
        });
        return RespBean.ok("ok", historyInfos);
    }

    private void populateHistoryInfo(ArrayList<HistoryInfo> historyInfos, HistoryInfo historyInfo, Date startTime, Date endTime, HistoricVariableInstance historicVariableInstance) {
        String variableName = historicVariableInstance.getVariableName();
        Object value = historicVariableInstance.getValue();
        if ("reason".equals(variableName)) {
            historyInfo.setReason((String) value);
        } else if ("days".equals(variableName)) {
            historyInfo.setDays(Integer.parseInt(value.toString()));
        } else if ("approved".equals(variableName)) {
            Boolean v = (Boolean) value;
            if (v) {
                historyInfo.setStatus(1);
            }else{
                historyInfo.setStatus(2);
            }
        } else if ("name".equals(variableName)) {
            historyInfo.setName((String) value);
        } else if ("approveUser".equals(variableName)) {
            historyInfo.getApproveUsers().add((String) value);
        }
        historyInfo.setStatus(3);
        historyInfo.setStartTime(startTime);
        historyInfo.setEndTime(endTime);
        historyInfos.add(historyInfo);
    }
}
