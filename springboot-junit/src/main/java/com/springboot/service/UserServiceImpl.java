package com.springboot.service;

import com.springboot.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDaoImpl userDao;

    public void insertUser() {
        userDao.insertUser();
    }

}