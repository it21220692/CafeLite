package com.cafelite.model.DTO;

import java.io.InputStream;
//This is a model class represents a Menu entity
public class MenuDTO { //encapsulation
	private int menuID;
	private String foodName;
	private String foodCategory;
	private String price;
	private String availability;
	private String quantity;
	private String image;

	public MenuDTO(int menuID, String foodName, String foodCategory, String price, String availability, String quantity,
			String image) {
		super();
		this.menuID = menuID;
		this.foodName = foodName;
		this.foodCategory = foodCategory;
		this.price = price;
		this.availability = availability;
		this.quantity = quantity;
		this.image = image;
	}
	
	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

