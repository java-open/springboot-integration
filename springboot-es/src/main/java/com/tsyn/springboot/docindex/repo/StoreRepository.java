package com.tsyn.springboot.docindex.repo;

import com.tsyn.springboot.document.StoreDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 门店Repository
 * @author 李锋镝
 * @date Create at 09:30 2019/8/23
 */
public interface StoreRepository extends ElasticsearchRepository<StoreDocument, String> { }