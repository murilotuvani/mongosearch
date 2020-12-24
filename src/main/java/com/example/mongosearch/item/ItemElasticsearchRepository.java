package com.example.mongosearch.item;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemElasticsearchRepository extends ElasticsearchRepository<Item, String> {

}
