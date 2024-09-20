package com.yym.flowable.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * @Description: 流程历史记录
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 15:23
 */
@Data
public class HistoryInfo {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date endTime;
    private String name;
    private String reason;
    private Integer status;
    private Integer days;
    private List<String> approveUsers = new ArrayList<>();
    private List<String> candidateUsers = new ArrayList<>();

}
