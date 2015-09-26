package com.avnet.storepal.beacon;

public class Item {

	private String itemName;
	private String itemPrice;
	private String itemPromo;
	private int sectionId;
	private String reviewComments;
	private int reviewStars;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemPromo() {
		return itemPromo;
	}
	public void setItemPromo(String itemPromo) {
		this.itemPromo = itemPromo;
	}
	public Item(String itemName, String itemPrice, String itemPromo, String reviewComments, int reviewStars) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemPromo = itemPromo;
		this.reviewComments = reviewComments;
		this.reviewStars = reviewStars;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}
	public int getReviewStars() {
		return reviewStars;
	}
	public void setReviewStars(int reviewStars) {
		this.reviewStars = reviewStars;
	}
	
	
	
}
