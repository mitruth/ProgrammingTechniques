package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.MInt;
import model.Monom;

public class IntTest {

	@Test
	public void test() {
		MInt m1 = new MInt(5,6);
		MInt m2 = new MInt(6,1);
		Monom m3 = m1.integrate();
		assertEquals(m2.getCoeff(), m3.getCoeff());
		assertEquals(m2.getGrade(), m3.getGrade());
	}

}
