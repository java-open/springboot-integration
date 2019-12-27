package com.tsyh.springdatajest.repository;
// ProductRepository.java

import com.tsyh.springdatajest.dataobject.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {

}
