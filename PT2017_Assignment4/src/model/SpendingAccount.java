package model;

public class SpendingAccount extends Account{

	public SpendingAccount(int accountID, Person p, double dep) {
		super(accountID, p, dep);
	}
	
	public void increaseDeposit(double addedDeposit) {
		super.increaseDeposit(addedDeposit);
	}
	
	public void withdrawDeposit(double removedDeposit) {
		super.withdrawDeposit(removedDeposit);
	}
}
