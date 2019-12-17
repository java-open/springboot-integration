package com.spring.springboot.entity;

import java.io.Serializable;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/11/27
*/

@Data
public class City implements Serializable {
    /**
	* 城市编号
	*/
    private Integer id;

    /**
	* 省份编号
	*/
    private Integer provinceId;

    /**
	* 城市名
	*/
    private String cityName;

    /**
	* 描述
	*/
    private String description;

    private static final long serialVersionUID = 1L;
}