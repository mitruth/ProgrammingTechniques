package model;

public class Order {

	private int clientOrderID;
	private int productOrderID;
	private int orderQuantity;
	
	public Order(int clientId, int productId, int quantity) {
		this.clientOrderID = clientId;
		this.productOrderID = productId;
		this.orderQuantity = quantity;
	}
	
	public int getClientOrderID() {
		return this.clientOrderID;
	}

	public int getProductOrderID() {
		return this.productOrderID;
	}
	
	public int getOrderQuantity() {
		return this.orderQuantity;
	}
	
//	public String toString() {
//		String order = "";
//		order = "The Client: " + this.getClient().toString();
//		if (!this.getProductList().isEmpty()) {
//			order += " has ordered the following products:" + "\n";
//			for (Product p : productList) {
//				order += p.getProductName() + "\n";
//			}
//		} else {
//			order += " has not ordered any products!";
//		}
//		return order;
//	}
}
