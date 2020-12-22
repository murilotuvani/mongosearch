package com.example.mongosearch.item;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface ItemMongoRepository extends MongoRepository<Item, String> {

}
