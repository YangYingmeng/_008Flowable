package com.yym.flowable.model;

import lombok.Data;

/**
 * @Description: 响应bean
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 14:39
 */
@Data
public class RespBean {

    private String msg;
    private Integer status;
    private Object data;

    public static RespBean ok(String msg, Object data) {
        return new RespBean(200, msg, data);
    }


    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }


    public static RespBean error(String msg, Object data) {
        return new RespBean(500, msg, data);
    }


    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
