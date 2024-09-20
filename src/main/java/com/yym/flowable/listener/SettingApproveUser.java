package com.yym.flowable.listener;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @Description: SettingApproveUser
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:40
 */

public class SettingApproveUser implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String approveType = (String) delegateTask.getVariable("approveType");
        if ("by_user".equals(approveType)) {
            delegateTask.setAssignee((String) delegateTask.getVariable("approveUser"));
        } else if ("by_role".equals(approveType)) {
            Object approveRole = delegateTask.getVariable("approveRole");
            delegateTask.addCandidateGroup((String) approveRole);
        }
    }
}
