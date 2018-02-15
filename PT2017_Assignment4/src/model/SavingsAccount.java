package model;

public class SavingsAccount extends Account{

	/**
	 * 
	 */
	public static final double interest = 0.1;
	
	public SavingsAccount(int id, Person p, double deposit) {
		super(id, p, deposit);
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Account#decreaseDeposit(double)
	 * we can only withdraw the whole deposit from the account
	 */
	public void withdrawDeposit(double depositToBeWithdrawn) {
		super.withdrawDeposit(super.getDeposit()); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Account#increaseDeposit(double)
	 * if we want to deposit money, we will be charged with an interest rate of 0.1
	 */
	public void increaseDeposit(double depositToBeAdded) {
		super.increaseDeposit(depositToBeAdded - depositToBeAdded * interest);
	}
}
