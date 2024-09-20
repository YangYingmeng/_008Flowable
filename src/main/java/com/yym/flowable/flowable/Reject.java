package com.yym.flowable.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 拒绝策略(可以在这里发邮件 发短信)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 15:16
 */
@Slf4j
public class Reject implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("请假被拒绝: {}", delegateExecution.getVariables());
    }
}
