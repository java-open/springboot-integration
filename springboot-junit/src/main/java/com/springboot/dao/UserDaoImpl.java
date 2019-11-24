package com.springboot.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {

    public void insertUser() {
        System.out.println("添加用户成功！");
    }

}