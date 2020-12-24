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
		StringBuilder sbThread = new StringBuilder("SecondaryEndpoint.Thread(id=\"");
		sbThread.append(Thread.currentThread().getId())
		        .append("\", name=\"")
		        .append(Thread.currentThread().getName())
				.append("\", item.id=")
				.append(item.getId()).append(")");
		System.out.println(sbThread.toString());
		logger.info(sbThread.toString());
	}

}
