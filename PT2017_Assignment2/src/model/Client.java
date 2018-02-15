package model;

public class Client{

	private int arrivalTime;
	private int serviceTime;
	private String ID;
	
	public Client (int arrivalTime, int serviceTime, String ID) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.ID = ID;
	}
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	public int getServiceTime() {
		return this.serviceTime;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setID (String name) {
		this.ID = name;
	}
	
	public void setArrivalTime(int arrT) {
		this.arrivalTime = arrT;
	}
	
	public void setServiceTime(int serT) {
		this.serviceTime = serT;
	}
	
	public void addTime(int x) {
		this.serviceTime = this.serviceTime + x;
	}
	
	@Override
	public String toString() {
		return "Client " + this.ID + " arrives at: " + this.getArrivalTime() + " and has a service time of: " + this.getServiceTime();
	}
}
