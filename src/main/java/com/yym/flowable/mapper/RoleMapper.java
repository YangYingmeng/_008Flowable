package com.yym.flowable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yym.flowable.model.Role;

/**
 * @Description: RoleMapper
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:22
 */
@Mapper
public interface RoleMapper {

    List<Role> getAllRoles();
}
