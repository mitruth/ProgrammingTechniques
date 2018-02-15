package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bank;
import model.Person;

public class AddPersonTest {

	@Test
	public void test() {
		Person p = new Person(1, "Adela");
		Bank b = new Bank();
		b = b.writeAccountData();
		int sizeBefore = b.getPersonSize();
		b.addPerson(p);
		int sizeAfter = b.getPersonSize();
		assertEquals("Test", sizeBefore + 1, sizeAfter);
	}

}
