package com.spring.springboot.entity;

import java.io.Serializable;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/27
*/
@Data
public class User implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 描述
     */
    private String description;
    /**
     * 关联从数据库对象
     */
    private City city;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_DESCRIPTION = "description";
}