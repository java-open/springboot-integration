package com.spring.springboot.service;

import com.spring.springboot.dao.master.UserDao;
import com.spring.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.spring.springboot.entity.City;
import com.spring.springboot.dao.cluster.CityDao;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源


    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("上海市");
        System.out.println(city);
        user.setDescription("从数据库->"+city.getDescription());
        user.setCity(city);
        return user;
    }
}


