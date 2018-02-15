package model;

public class Client {
	
	private int ID;
	private String name;
	
	public Client (int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	public Client(String name) {
		this.name = name;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setClientID(int id) {
		this.ID = id;;
	}
	
	public String toString() {
		return "Client " + this.getName() + " , with the ID: " + this.getID() + "\n";
	}
}
