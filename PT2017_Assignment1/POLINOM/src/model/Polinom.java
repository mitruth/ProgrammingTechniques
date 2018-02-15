package model;
import view.*;
import java.util.ArrayList;
import javax.swing.JTextField;

import view.GraphicDesign;

public class Polinom {

	ArrayList < Monom > elements;//list of monomials that form our polynomial
	
	public Polinom()//initializa a polynomial as an empty array list
	{
		this.elements = new ArrayList<>();
	}
	
	public void addElement(Monom m)//method that adds a monomial to the list of monomials of a polynomial
	{
		this.elements.add(m);
	}
	
	public int getPolDegree (Polinom x) { //given a polynomial as a parameter, returns the degree of it, as in, 
		//the maximum degree of the monomials situated in it
		int maxim = 0;
		for (Monom m : x.elements) {
			maxim = m.getGrade();
		}
		return maxim;
	}
	
	public Polinom add(Polinom x)//method to add two polynomials, if the degrees of the polynomials are different,
	//it adds the monomials of the polynomial with the bigger degree to the result
	{
		Polinom sum = new Polinom();
		if (getPolDegree(this) > getPolDegree(x)) {
			for(Monom m : this.elements) {
				Monom m2;
				int t = 0;
				for(Monom m1 : x.elements) {
					if(m.getGrade() == m1.getGrade()) {
						t = 1;
						m2 = m.add(m1);
						m2.grade = m.grade;
						sum.addElement(m2);
						break;
					}
				}
				if (t == 0) {
					sum.addElement(m);
				}
			}
		} else {
			for(Monom m : x.elements) {
				Monom m2;
				int t = 0;
				for(Monom m1 : this.elements) {
					if(m.getGrade() == m1.getGrade()) {
						t = 1;
						m2 = m.add(m1);
						m2.grade = m.grade;
						sum.addElement(m2);
						break;
					}
				}
				if (t == 0) {
					sum.addElement(m);
				}
			}
		}
		return sum;
	}
	
	public Polinom subtract (Polinom x) {//method to subtract two polynomials, if the degrees of the polynomials are different,
		//it subtracts the monomials of the polynomial with the bigger degree from zero
		Polinom difference = new Polinom();
		if (getPolDegree(this) > getPolDegree(x)) {
			for(Monom m : this.elements) {
				Monom m2;
				int t = 0;
				for(Monom m1 : x.elements) {
					if(m.getGrade() == m1.getGrade()) {
						t = 1;
						m2 = m.subtract(m1);
						m2.grade = m.grade;
						difference.addElement(m2);
						break;
					}
				}
				if (t == 0) {
					difference.addElement(m);
				}
			}
		} else {
			for(Monom m : x.elements) {
				Monom m2;
				int t = 0;
				for(Monom m1 : this.elements) {
					if(m.getGrade() == m1.getGrade()) {
						t = 1;
						m2 = m1.subtract(m);
						m2.grade = m.grade;
						difference.addElement(m2);
						break;
					}
				}
				if (t == 0) {
					m.setCoeff(-1 * m.getCoeff().intValue());
					difference.addElement(m);
				}
			}
		}
		return difference;
	}
	
	public Polinom multiply (Polinom x) { // method to multiply two polynomials
		Polinom multiplied = new Polinom();
		for (Monom m: this.elements) {
			Monom m2;
			for (Monom m1: x.elements) {
				m2 = m.multiply(m1);
				multiplied.addElement(m2);
			}
		}
		return multiplied;
	}

	public Polinom derive () { //method to derive a polynomial

		Polinom derivative = new Polinom();
		for (Monom m: this.elements) {
			Monom m1 = m.derive();
			derivative.addElement(m1);
		}
		return derivative;
	}
	
	public Polinom integrate () {//method to integrate a polynomial
		Polinom integrated = new Polinom();
		for (Monom m: this.elements) {
			Monom m1 = m.integrate();
			integrated.addElement(m1);
		}
		return integrated;
	}
	
	public String printPolinom() //prints a polynomial on the string out
	{
		String out = "";
		for (Monom m : this.elements) {
			if(m.getCoeff().doubleValue() != 0 ) {
				if( m.getGrade() > 0 && m.getCoeff().doubleValue() > 0) {
					out=out+"+";
				}
				out+= m.toString();
			} 
		}
		return out;
	}
	
	public void readPolinom(String s) {//reads a polynomial, it receives as a parameter a string containing
		//the coefficients of the polynomial in ascending order of the degree
		String[] terms = s.split(" ");
		for (int i = 0; i < terms.length; i++) {
			Monom m = new MInt (i, Integer.parseInt(terms[i]));
			this.addElement(m);
		}
	}
}
