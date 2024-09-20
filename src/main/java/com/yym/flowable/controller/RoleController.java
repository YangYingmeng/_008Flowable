package com.yym.flowable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yym.flowable.model.RespBean;
import com.yym.flowable.service.RoleService;

import lombok.RequiredArgsConstructor;

/**
 * @Description: RoleController
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:35
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public RespBean getAllRoles() {
        return RespBean.ok("OK",roleService.getAllRoles());
    }
}
