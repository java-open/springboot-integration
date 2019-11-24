package com.springboot.service;

import com.springboot.pojo.User;

import java.util.List;

public interface UserServiceI {

    int insertUser(User user);

    // 查询用户
    List<User> selectUserList();

    // 根据主键查询用户
    User selectUserById(Integer id);

    // 修改用户
    int updateUser(User user);

    // 删除用户
    int deleteUser(Integer id);

}
