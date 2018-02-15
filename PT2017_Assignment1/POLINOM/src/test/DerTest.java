package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.MInt;
import model.Monom;

public class DerTest {

	@Test
	public void test() {
		MInt m1 = new MInt(5,5);
		MInt m2 = new MInt(4,25);
		Monom m3 = m1.derive();
		assertEquals(m2.getCoeff(), m3.getCoeff());
		assertEquals(m2.getGrade(), m3.getGrade());
	}
}
