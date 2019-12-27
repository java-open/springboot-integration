package com.tsyn.springboot.document.store;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class StoreTags {
    @Field(type = FieldType.Keyword)
    private String key;

    @Field(type = FieldType.Keyword)
    private String value;

    private String showName;
}