package com.example.mongosearch.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	ItemMongoRepository itemMongoRepository;

	public void save(Item item) {
		itemMongoRepository.save(item);
	}
	
	public List<Item> list() {
		return itemMongoRepository.findAll();
	}

}
