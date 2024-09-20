package com.yym.flowable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yym.flowable.model.ApproveRejectVO;
import com.yym.flowable.model.AskForLeaveVO;
import com.yym.flowable.model.RespBean;
import com.yym.flowable.service.AskForLeaveService;

import lombok.RequiredArgsConstructor;

/**
 * @Description: 处理请假接口
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 14:42
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AskForLeaveController {

    private final AskForLeaveService askForLeaveService;

    @PostMapping("/ask_for_leave")
    public RespBean askForLeave(@RequestBody AskForLeaveVO askForLeaveVO) {
        return askForLeaveService.askForLeave(askForLeaveVO);
    }


    @GetMapping("/list")
    public RespBean leaveList() {
        return askForLeaveService.leaveList();
    }

    @PostMapping("/handler")
    public RespBean askForLeaveHandler(@RequestBody ApproveRejectVO approveRejectVO) {
        return askForLeaveService.askForLeaveHandler(approveRejectVO);
    }

    @GetMapping("/search")
    public RespBean searchResult() {
        return askForLeaveService.searchResult();
    }
}
