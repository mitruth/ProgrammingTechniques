package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bank;

public class AddAccountTest {

	@Test
	public void test() {
		Bank b = new Bank();
		b = b.writeAccountData();
		int sizeBefore = b.getAccounts().size();
		b.addAccount(1, 1, 1);
		int sizeAfter = b.getAccounts().size();
		assertEquals(sizeBefore + 1, sizeAfter);
	}
}
