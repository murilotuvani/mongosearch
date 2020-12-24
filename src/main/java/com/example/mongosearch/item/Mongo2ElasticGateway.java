package com.example.mongosearch.item;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.scheduling.annotation.Async;

@MessagingGateway(name="mongo2elasticGateway", defaultRequestChannel = "mongo2elasticChannel")
public interface Mongo2ElasticGateway {
	
	@Gateway
	@Async
	void syncIt(Item item);

}
