package model;

public class MInt extends Monom{
	
	private Integer coeff;//i redefine the coefficient with the Integer data type, to handle polynomials with
	//integer coefficients

	public MInt (int grade, Integer coeff) {
		this.grade = grade;
		this.coeff = coeff;
	}
	
	public Integer getCoeff() {
		return (Integer)coeff;
	}
	
	public void setCoeff(Number c) {
		this.coeff = (Integer)c;
	}
	
	public Monom add (Monom m) { //addition method for monomials with integer coefficients
		if((double)m.getCoeff().intValue() != m.getCoeff().doubleValue()) {
			return m.add(this);
		} else {
			Monom m1 = new MInt(m.getGrade(), this.getCoeff().intValue() + m.getCoeff().intValue());
			return m1;
		}
	}
	
	public Monom subtract (Monom m) {//subtraction method for monomials with integer coefficients
		Monom m1 = new MInt (m.getGrade(), this.getCoeff().intValue() - m.getCoeff().intValue());
		return m1;
	}
	
	public Monom multiply(Monom m) {//multiplication method for monomials with integer coefficients
		if (m.coeff.doubleValue() % 1 != 0) {
			return m.multiply(this);
		} else {
			Monom m1 = new MInt (this.getGrade() + m.getGrade(), this.getCoeff() * m.getCoeff().intValue());
			return m1;
		}
	}

	public Monom derive() {//derivation method for monomials with integer coefficients
			Monom m1 = new MInt((this.getGrade() - 1), this.getGrade() * this.getCoeff().intValue());
			return m1;
		}
	
	public Monom integrate() {//integration method for monomials with integer coefficients
		if ((this.getCoeff().doubleValue() / (this.getGrade() + 1)) % 1 != 0) {
			Monom m1 = new MReal (this.getGrade(), this.getCoeff().doubleValue());
			return m1.integrate();
		} else {
			Monom m1 = new MInt (this.getGrade() + 1, this.getCoeff().intValue() / (this.getGrade() + 1));
			return m1;
		}
}
	
	public String toString() {//I override the toString method so I can display the values in a pleasant format, as I please
		if (this.getGrade() > 1) {
		 return String.format(this.getCoeff().intValue() + "x^" + this.getGrade());
		} else if (this.getGrade() == 1){
			return String.format(this.getCoeff().intValue() + "x");
		} else {
			return String.format(this.getCoeff().intValue() + "");
		}
	}
}
