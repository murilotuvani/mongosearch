package com.example.mongosearch.item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	ItemMongoRepository itemMongoRepository;
	
	@Autowired
	Mongo2ElasticGateway mongo2ElasticGateway;
	
	@Autowired
	ElasticsearchOperations elasticsearchOperations;
	
//	@Autowired
//	ItemElasticsearchRepository itemElasticsearchRepository;

	
	public Optional<Item> findById(String id) {
		return itemMongoRepository.findById(id);
	}
	
	public Item save(Item item) {
		Item nitem = itemMongoRepository.save(item);
		StringBuilder sbThread = new StringBuilder("ItemService.Thread(id=\"");
		
		sbThread.append(Thread.currentThread().getId())
		        .append("\", name=\"")
		        .append(Thread.currentThread().getName())
		        .append("\")");
		System.out.println(sbThread.toString());
		mongo2ElasticGateway.syncIt(nitem);
		return nitem;
	}
	
	public List<Item> list() {
		return itemMongoRepository.findAll();
	}

	public void delete(Item item) {
		itemMongoRepository.delete(item);
		
	}

	public List<Item> search(String k) {
		
		QueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery(k, "title", "description")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
		
//		QueryShardContext sourceBuilder;
//		matchQueryBuilder.toQuery(shardContext)
//		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);
//        // quantidadde de elementos a serem retornados pela pesquisa
//        sourceBuilder.size(25);
//        sourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
//        sourceBuilder.set
		
//		//https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#repositories.namespace-reference
//		NativeSearchQuery query = new NativeSearchQueryBuilder()
		NativeSearchQuery query = new NativeSearchQueryBuilder()
				.withQuery(matchQueryBuilder).build();
		
		
		SearchHits<Item> response = elasticsearchOperations.search(query, Item.class);
		List<Item> items = response.getSearchHits().stream().map(hit -> hit.getContent()).collect(Collectors.toList());
		return items;
	}

}
