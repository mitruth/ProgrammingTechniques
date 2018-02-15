package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SpendingAccount;

public class DepositTest {

	@Test
	public void test() {
		Person p = new Person(1, "Dan");
		Account a = new SpendingAccount(15, p, 0);
		Bank b = new Bank();
		b = b.writeAccountData();
		b.addAccount(1, 15, 1);
		b.depositIntoAccount(15, 100);
		double newDeposit = a.getDeposit();
		assertEquals(100, (int)newDeposit);
	}

}
