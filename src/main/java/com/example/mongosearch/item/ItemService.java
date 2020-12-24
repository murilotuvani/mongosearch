package com.example.mongosearch.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	ItemMongoRepository itemMongoRepository;

	
	public Optional<Item> findById(String id) {
		return itemMongoRepository.findById(id);
	}
	
	public Item save(Item item) {
		return itemMongoRepository.save(item);
	}
	
	public List<Item> list() {
		return itemMongoRepository.findAll();
	}

	public void delete(Item item) {
		itemMongoRepository.delete(item);
	}

}
