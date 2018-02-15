package model;

import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountID;
	private Person person;
	private double deposit;
	private boolean deleted;
	
	public Account (int id, Person p, double d) {
		this.accountID = id;
		this.person = p;
		this.deposit = d;
		this.deleted = false;
	}
	
	public double getDeposit() {
		return this.deposit;
	}
	
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	/*
	 * @param depositToBeAdded
	 * increases the current deposit by the amount specified as a parameter
	 */
	public void increaseDeposit(double depositToBeAdded) {
		this.deposit += depositToBeAdded;
	}
	
	/*
	 * @param depositToBeDecreased
	 * decreases the current deposit by the amount specified as a parameter
	 */
	public void withdrawDeposit(double depositToBeDecreased) {
		this.deposit -= depositToBeDecreased;
	}
	
	public int getAccountID() {
		return this.accountID;
	}
	
	public void setAccountID(int ID) {
		this.accountID = ID;
	}
	
	public Person getPerson() {
		return this.person;
	}
	
	public void setPerson(Person p) {
		this.person = p;
	}
	
	public void deleteAccount() {
		this.deleted = true;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}
	
	@Override
	public String toString() {
		return "Account: " + this.getAccountID() + ", with the person: " + this.getPerson().toString() + " and deposit: " + this.getDeposit();
	}
}
