package com.example.mongosearch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.query.IndexQuery;
//import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @see https://docs.spring.io/spring-data/elasticsearch/docs/4.1.2/reference/html/#reference
 * @author murilotuvani
 */
@MessageEndpoint
public class Mongo2ElasticEndpoint {

	private final Logger logger = LoggerFactory.getLogger(Mongo2ElasticEndpoint.class);

	@Autowired
	ItemElasticsearchRepository elasticsearchRepository;

	@ServiceActivator(inputChannel = "mongo2elasticChannel")
	public void indexItem(Item item) {

		StringBuilder sbThread = new StringBuilder("Mongo2ElasticEndpoint.Thread(id=\"");
		sbThread.append(Thread.currentThread().getId()).append("\", name=\"").append(Thread.currentThread().getName())
				.append("\", item.id=").append(item.getId()).append(")");
		System.out.println(sbThread.toString());
		logger.info(sbThread.toString());
		
		Item a = elasticsearchRepository.save(item);
		System.out.println("ItemElasticsearchRepository.save() => " + a);
//		IndexQuery indexQuery = new IndexQueryBuilder()
//			      .withId(item.getId())
//			      .withObject(item)
//			      .build();
//				
//		highLevelClient.indexAsync(null, null, null)
	}
}
