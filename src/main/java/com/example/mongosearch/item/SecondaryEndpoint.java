package com.example.mongosearch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class SecondaryEndpoint {
	
	private final Logger logger = LoggerFactory.getLogger(Mongo2ElasticEndpoint.class);

	@ServiceActivator(inputChannel = "mongo2elasticChannel")
	public void handleDiscardedCargo(Item item) {
		logger.info("SecondaryEndpoint in Batch = " + item.getId());
		System.out.println("SecondaryEndpoint message : " + item.getId());
	}

}
