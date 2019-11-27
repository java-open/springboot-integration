package com.spring.springboot.dao.cluster;

import com.spring.springboot.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2019/11/27
*/
@Mapper
public interface CityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    City findByName(@Param("name") String name);
}