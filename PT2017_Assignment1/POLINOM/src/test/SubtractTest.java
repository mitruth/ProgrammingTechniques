package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.MInt;
import model.Monom;

public class SubtractTest {

	public void test() {
		MInt m1 = new MInt(5,5);
		MInt m2 = new MInt(5,3);
		MInt m3 = new MInt(5,2);
		Monom m4 = m1.subtract(m2);
		assertEquals(m3.getCoeff(), m4.getCoeff());
		assertEquals(m3.getGrade(), m4.getGrade());
	}
}