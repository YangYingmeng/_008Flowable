package com.yym.flowable.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 同意策略(可以在这里发邮件 发短信)
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 15:14
 */
@Slf4j
public class Approve implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("申请通过: {}", delegateExecution.getVariables());
    }
}
