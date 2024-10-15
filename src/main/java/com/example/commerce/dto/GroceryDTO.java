package com.example.commerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroceryDTO {
	private Integer groceryId;
	private String groceryName;
	private String description;
	private String subcategory;
	private String category;
	private String name;
	private String size;
	private Double price;
	private Integer quantity;
	private String storeName;
	private String brand;

	public GroceryDTO(String name, String size, Double price, Integer quantity, String storeName) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		this.storeName = storeName;
	}

	public GroceryDTO(Integer groceryId, String groceryName, String description, String subcategory, String category,
			String size, Double price, Integer quantity, String brand) {
		super();
		this.groceryId = groceryId;
		this.groceryName = groceryName;
		this.description = description;
		this.subcategory = subcategory;
		this.category = category;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
	}

}
