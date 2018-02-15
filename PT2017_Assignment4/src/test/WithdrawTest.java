package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SpendingAccount;

public class WithdrawTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Person p = new Person(1, "Dan");
		Account a = new SpendingAccount(1, p, 300);
		Bank b = new Bank();
		
		b.WithdrawFromAccount(1, 100);
		double newDeposit = a.getDeposit();
		assertEquals(200, newDeposit);
	}

}
