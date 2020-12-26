package com.example.mongosearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
	

	@Override
	@Bean(destroyMethod = "close")
	public RestHighLevelClient elasticsearchClient() {
		System.out.println("Creating Elasticsearch's RestHighLevelClient");
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo("localhost:9200")
				.build();
		
		RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
		
		return client;
	}
	
}
