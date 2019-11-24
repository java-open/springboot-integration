package com.springboot.pojo;

import com.springboot.group.ValidateGroupForAll;
import com.springboot.group.ValidateGroupForName;

import javax.validation.constraints.*;
import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    @NotEmpty(message = "用户名不可以为空", groups = {ValidateGroupForName.class, ValidateGroupForAll.class})// 非空校验不会去除前后空格
    private String username;
    @NotBlank(message = "密码不可以为空", groups = {ValidateGroupForAll.class})// 非空校验会去除前后空格
    @Size(message = "且长度在6 ~ 10之间", min = 6, max = 10, groups = {ValidateGroupForAll.class})// 长度必须在6~10之间
    private String password;
    // 校验是否为数字，并且在规定的大小值内
    @Max(message = "年龄必须在1 ~ 150岁之间", value = 150, groups = {ValidateGroupForAll.class})// 必须小于等于指定的值
    @Min(message = "年龄必须在1 ~ 150岁之间", value = 1, groups = {ValidateGroupForAll.class})// 必须大于等于指定的值
    private Integer age;
    // 校验邮箱格式，默认正则匹配规则是 ".*"
    @Email(message = "请输入正确的邮箱格式：xx@xx.com", regexp = "[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}",
            groups = {ValidateGroupForAll.class})
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
    
}