package com.shsxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 * Spring Boot 便提供了一个统一的注解 @SpringBootApplication
 */
//该注解只扫描同包下的服务
@SpringBootApplication
public class App {
    // 项目中不需要有多个 main 方法只需要有一个入口就可以了
    public static void main(String[] args) {
// 主函数运行 springboot 项目
        SpringApplication.run(App.class, args);
    }
}
