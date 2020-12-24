package com.example.mongosearch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class Mongo2ElasticEndpoint {

	private final Logger logger = LoggerFactory.getLogger(Mongo2ElasticEndpoint.class);

	@ServiceActivator(inputChannel = "mongo2elasticChannel")
	public void handleDiscardedCargo(Item item) {
		logger.info("Message in Batch = " + item.getId());
		System.out.println("Mongo2ElasticEndpoint sysout message : " + item.getId());
	}
}
