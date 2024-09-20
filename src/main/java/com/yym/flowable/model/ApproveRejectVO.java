package com.yym.flowable.model;

import lombok.Data;

/**
 * @Description: ApproveRejectVO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 15:03
 */
@Data
public class ApproveRejectVO {

    private String taskId;
    private Boolean approve;
    private String name;
}
