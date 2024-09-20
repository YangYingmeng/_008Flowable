package com.yym.flowable.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yym.flowable.model.Role;
import com.yym.flowable.model.User;

/**
 * @Description: UserMapper
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:21
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getUserRolesByUserId(Integer uid);

    List<User> getAllUsers();
}
