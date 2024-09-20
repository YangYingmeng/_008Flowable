package com.yym.flowable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yym.flowable.mapper.UserMapper;
import com.yym.flowable.model.User;

import lombok.RequiredArgsConstructor;

/**
 * @Description: MyUserDetailsService
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/11/2 16:20
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (null == user) {
            throw new RuntimeException("用户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUserId(user.getId()));
        return user;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
}
