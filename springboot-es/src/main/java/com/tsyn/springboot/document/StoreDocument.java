package com.tsyn.springboot.document;

import com.tsyn.springboot.document.store.StoreBaseInfo;
import com.tsyn.springboot.document.store.StoreTags;
import com.tsyn.springboot.search.annotation.DefinitionQuery;
import com.tsyn.springboot.search.enums.QueryTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 门店Document
 * 创建ES文档和映射
 * 首先创建一个JAVA对象，然后通过注解来声明字段的映射属性。
 * spring提供的注解有@Document、@Id、@Field，其中@Document作用在类，@Id、@Field作用在成员变量，@Id标记一个字段作为id主键。
 *
 */
@Document(indexName = "store", type = "base")
@Data
@DefinitionQuery(key = "page", type = QueryTypeEnum.IGNORE)
@DefinitionQuery(key = "size", type = QueryTypeEnum.IGNORE)
@DefinitionQuery(key = "q", type = QueryTypeEnum.FULLTEXT)
public class StoreDocument {

    @Id
    @DefinitionQuery(type = QueryTypeEnum.IN)
    @DefinitionQuery(key = "id", type = QueryTypeEnum.IN)
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 基础信息
     */
    @Field(type = FieldType.Object)
    private StoreBaseInfo baseInfo;

    /**
     * 标签
     */
    @Field(type = FieldType.Nested)
    @DefinitionQuery(key = "tagCode", mapped = "tags.key", type = QueryTypeEnum.IN)
    @DefinitionQuery(key = "tagValue", mapped = "tags.value", type = QueryTypeEnum.AND)
    @DefinitionQuery(key = "_tagValue", mapped = "tags.value", type = QueryTypeEnum.IN)
    private List<StoreTags> tags;

}