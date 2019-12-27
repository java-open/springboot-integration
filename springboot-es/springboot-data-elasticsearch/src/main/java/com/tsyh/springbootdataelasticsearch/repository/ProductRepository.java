package com.tsyh.springbootdataelasticsearch.repository;

import com.tsyh.springbootdataelasticsearch.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}