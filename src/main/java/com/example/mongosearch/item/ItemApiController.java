package com.example.mongosearch.item;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemApiController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ItemApiController.class);

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Item> getById(@PathVariable String id) {
		LOG.debug("getById{}", id);
		ResponseEntity<Item> response = itemService.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		return response;
	}

	/**
	 * curl --header "Content-Type: application/json" http://localhost:8080/api/item
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Item>> list() {
		LOG.debug("list()");
		List<Item> list = itemService.list();
		return ResponseEntity.ok(list);
	}

	/**
	 * 
	 * 	String title;
	String description;
	BigDecimal price;
	Long ean13;
	
	
	 curl --header "Content-Type: application/json" \
	      --request POST \
	      --data '{"title": "Soda Diet Coke Cube Pack","description": "Soda Diet Coke Cube Pack.","price": 0.95, "ean13": 49000010633}' \
	      http://localhost:8080/api/item
	      
	      
	 curl --header "Content-Type: application/json" \
	      --request POST \
	      --data '{"title": "Muscle Milk Coffee House, Mocha Latte Non Dairy Protein Shake, 11 oz Bottles, 4 Count","description": "Muscle Milk Coffee House, Mocha Latte Non Dairy Protein Shake, 11 oz Bottles, 4 Count.","price": 6.65, "ean13": 876063006460}' \
	      http://localhost:8080/api/item
	      
	 * 
	 * @param item
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Item> save(@RequestBody Item item) {
		LOG.debug("save({})", item);
		Item opItem = itemService.save(item);
		// Ok?
		return ResponseEntity.ok(opItem);

	}
	
	@DeleteMapping
	public ResponseEntity<Item> delete(@RequestBody Item item) {
		LOG.debug("delete({})", item);
		itemService.delete(item);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Item> update(@RequestBody Item item) {
		LOG.debug("update({})", item);
		Item nItem = itemService.save(item);
		return ResponseEntity.ok(nItem);
	}	

}
