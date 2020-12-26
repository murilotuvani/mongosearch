package com.example.mongosearch.item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(indexName = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = -9143671793297197930L;

	@Id
	String id;
	String title;
	String description;
	BigDecimal price;
	Long ean13;
	long version;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	LocalDateTime createdOn;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	LocalDateTime updatedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getEan13() {
		return ean13;
	}

	public void setEan13(Long ean13) {
		this.ean13 = ean13;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price + ", ean13="
				+ ean13 + ", version=" + version + "]";
	}

}
