package com.example.mongosearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
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
	 * This makes the channel work as expected,
	 * since the default channel will deliver
	 * the message just to the first endpoint.
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

}
