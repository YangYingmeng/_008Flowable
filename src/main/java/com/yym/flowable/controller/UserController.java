package com.yym.flowable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yym.flowable.model.RespBean;
import com.yym.flowable.service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

/**
 * @Description: UserController
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:37
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final MyUserDetailsService userDetailsService;

    @GetMapping("/users")
    public RespBean getUsers() {
        return RespBean.ok("OK",userDetailsService.getAllUsers());
    }
}
