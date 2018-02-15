package model;

public class Product {

	private int productID;
	private int quantity;
	private int stock;
	private String productName;
	
	public Product(int productID, int quantity, String productName, int stock) {
		this.productID = productID;
		this.quantity = quantity;
		this.productName = productName;
		this.stock = stock;
	}
	
	public Product(int quantity, String productName, int stock) {
		super();
		this.quantity = quantity;
		this.productName = productName;
		this.stock = stock;
	}
	
	public int getProductID() {
		return this.productID;
	}
	
	public void setProductID(int ID) {
		this.productID = ID;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public void setProductName(String name) {
		this.productName = name;
	}
	
	public String toString() {
		return "ID: " + this.getProductID() + " : " + this.getProductName() + "\n";
	}
}
