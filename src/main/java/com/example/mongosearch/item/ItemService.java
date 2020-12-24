package com.example.mongosearch.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	ItemMongoRepository itemMongoRepository;
	
	@Autowired
	Mongo2ElasticGateway mongo2ElasticGateway;

	
	public Optional<Item> findById(String id) {
		return itemMongoRepository.findById(id);
	}
	
	public Item save(Item item) {
		Item nitem = itemMongoRepository.save(item);
//		StringBuilder sb = new StringBuilder();
//		sb.append("id: ").append(nitem.id);
//		sb.append("title: ").append(nitem.title);
//		sb.append("ean13: ").append(nitem.ean13);
//		sb.append("description: ").append(nitem.description);
//		sb.append("price: ").append(nitem.price);
//		
//		mongo2ElasticGateway.syncIt(sb.toString());
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

}
