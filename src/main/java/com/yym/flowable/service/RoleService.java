package com.yym.flowable.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yym.flowable.mapper.RoleMapper;
import com.yym.flowable.model.RespBean;

import lombok.RequiredArgsConstructor;

/**
 * @Description: RoleService
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:36
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

    private final RoleMapper roleMapper;


    public Object getAllRoles() {
        return RespBean.ok("OK", roleMapper.getAllRoles());
    }
}
