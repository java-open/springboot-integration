package com.tsyh.springbootdataelasticsearch.dataobject;

import com.tsyh.springbootdataelasticsearch.constant.FieldAnalyzer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "product2", // 索引名
        type = "new",
        shards = 5, // 默认索引分区数
        replicas = 0, // 每个分区的备份数
        refreshInterval = "-1" // 刷新间隔
)
@Data
public class ESProductDO {

    /**
     * ID 主键
     */
    @Id
    private Integer id;

    /**
     * SPU 名字
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.text)
    private String name;
    /**
     * 卖点
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.text)
    private String sellPoint;
    /**
     * 描述
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.text)
    private String description;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 分类名
     */
    @Field(analyzer = FieldAnalyzer.IK_MAX_WORD, type = FieldType.text)
    private String categoryName;


}