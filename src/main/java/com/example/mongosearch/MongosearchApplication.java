package com.example.mongosearch;

import java.io.IOException;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

import com.example.mongosearch.item.ItemElasticsearchRepository;
import com.example.mongosearch.item.ItemMongoRepository;

// This is necessary because otherwise the componentScan will create the each repository twice and raise an exception.
@EnableMongoRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ItemElasticsearchRepository.class))
@EnableElasticsearchRepositories(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ItemMongoRepository.class))
@SpringBootApplication
public class MongosearchApplication {

	/**
	 * This makes the channel work as expected, since the default channel will
	 * deliver the message just to the first endpoint.
	 * 
	 * @return
	 */
	@Bean
	public MessageChannel pubsubChannel() {
		PublishSubscribeChannel channel = new PublishSubscribeChannel();
		channel.setApplySequence(false);
		return channel;
	}

	public static void main(String[] args) {
		SpringApplication.run(MongosearchApplication.class, args);
	}

	@Bean
	@Profile({ "dev" })
	CommandLineRunner init(RestHighLevelClient client) {
		return args -> {
			// GET /_cat/indices
			/** curl --header "Content-Type: application/json" \
                http://localhost:9200/_cat/indices
                */
			GetIndexRequest request = new GetIndexRequest("*");
			GetIndexResponse response;

			try {
				response = client.indices().get(request, RequestOptions.DEFAULT);
				String[] indixes = response.getIndices();
				if (indixes.length == 0) {
					System.out.println("Index not found");
					
					CreateIndexRequest crequest = new CreateIndexRequest("item");
					crequest.mapping(
					        "{\n" +
					        "  \"properties\": {\n" +
					        "    \"message\": {\n" +
					        "      \"type\": \"text\"\n" +
					        "    }\n" +
					        "  }\n" +
					        "}", 
					        XContentType.JSON);
					CreateIndexResponse cresponse = client.indices().create(crequest, RequestOptions.DEFAULT);
					System.out.println("Index " + cresponse.index() + " create request acknowledged : "
							+ cresponse.isAcknowledged());
					;
				} else {
					System.out.println("Index found :: " + indixes[0]);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		};

	}

}
