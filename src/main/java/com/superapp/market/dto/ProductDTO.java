package com.superapp.market.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = -1501615754376677230L;
	
	private String id;
	
	private String name;
	
	private String description;
	
	private Double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
