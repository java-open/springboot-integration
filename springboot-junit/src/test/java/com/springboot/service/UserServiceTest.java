package com.springboot.service;

import com.springboot.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @RunWith启动器
 *      SpringRunner 继承了 SpringJUnit4ClassRunner，无任何功能名字简短
 * @SpringBootTest(classes = {App.class})
 *      1. 当前类为 Spring Boot 的测试类
 *      2. 加载 Spring Boot 启动类启动 Spring Boot
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testInsertUser() {
        userService.insertUser();
    }

}
