package com.example.mongosearch.item;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name="mongo2elasticGateway", defaultRequestChannel = "mongo2elasticChannel")
public interface Mongo2ElasticGateway {
	
	@Gateway
	void syncIt(Item item);

}
