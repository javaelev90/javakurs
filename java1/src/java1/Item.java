package java1;

public class Item {
	private int artNumber;
	private double price;
	private String description;
	
	public Item(int artNumber, double price, String description) {
		this.artNumber = artNumber;
		this.price = price;
		this.description = description;
	}
	
	public int getArtNumber() {
		return artNumber;
	}
	public void setArtNumber(int artNumber) {
		this.artNumber = artNumber;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "\nArtnumber: "+artNumber+"\nPrice: "+price+"\nDescription: "+description;
	}
	
}
