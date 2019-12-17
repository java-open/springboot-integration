package com.spring.springboot.dao.master;

import com.spring.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2019/11/27
*/
@Mapper
public interface UserDao {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}