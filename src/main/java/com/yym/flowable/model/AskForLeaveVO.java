package com.yym.flowable.model;

import lombok.Data;

/**
 * @Description: 请假人VO
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 14:36
 */
@Data
public class AskForLeaveVO {

    private String name;
    private Integer days;
    private String reason;
    private String approveType;
    private String approveUser;
    private String approveRole;
}
