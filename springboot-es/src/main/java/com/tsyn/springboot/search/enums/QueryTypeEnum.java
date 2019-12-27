package com.tsyn.springboot.search.enums;


public enum QueryTypeEnum {

    /**
     * 等于
     */
    EQUAL,

    /**
     * 忽略大小写相等
     */
    EQUAL_IGNORE_CASE,

    /**
     * 范围
     */
    RANGE,

    /**
     * in
     */
    IN,

    IGNORE,

    /**
     * 搜索
     */
    FULLTEXT,

    /**
     * 匹配 和q搜索区分开
     */
    MATCH,

    /**
     * 模糊查询
     */
    FUZZY,

    /**
     * and
     */
    AND,

    /**
     * 多个查询字段匹配上一个即符合条件
     */
    SHOULD,

    /**
     * 前缀查询
     */
    PREFIX,

    /**
     * 聚合
     */
    AGGREGATION,

    ;
}