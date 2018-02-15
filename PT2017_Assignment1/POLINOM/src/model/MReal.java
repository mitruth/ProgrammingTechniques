package model;

public class MReal extends Monom {

	private Double coeff;

	public MReal(int grade, Double coefficient) {
		this.grade = grade;
		this.coeff = coefficient;
	}

	public Double getCoeff() {
		return (Double) coeff;
	}

	public void setCoeff(Number c) {
		this.coeff = (Double) c;
	}

	public Monom add(Monom m) {//addition method for monomials with real coefficients
		Monom m1 = new MReal(m.getGrade(), this.getCoeff().doubleValue() + m.getCoeff().doubleValue());
		return m1;
	}

	public Monom subtract (Monom m) {//subtraction method for monomials with real coefficients
		if ((int)this.getCoeff().doubleValue() != this.getCoeff().intValue() && (int)m.getCoeff().doubleValue() != m.getCoeff().intValue()) {
			return m.subtract(m);
		} else {
			Monom m1 = new MReal (m.getGrade(), this.getCoeff().doubleValue() - m.getCoeff().doubleValue());
			return m1;
		}
	}
	
	public Monom multiply(Monom m) {//multiplication method for monomials with real coefficients
		Monom m1 = new MReal (this.getGrade() + m.getGrade(), this.getCoeff() * m.getCoeff().doubleValue());
		return m1;
	}

	public Monom derive() {//derivation method for monomials with real coefficients
		Monom m1 = new MReal((this.getGrade() - 1), (double)this.getGrade() * this.getCoeff().doubleValue());
		return m1;
	}
	
	public Monom integrate() {//integration method for monomials with real coefficients
		Monom m1 = new MReal (this.getGrade() + 1, this.getCoeff().doubleValue() / ((double)this.getGrade() + 1));
		return m1;
	}

	public String toString() {//I override the toString method so I can display the values in a pleasant format, as I please
		if (this.getGrade() > 1) {
		 return String.format(this.getCoeff().doubleValue() + "x^" + this.getGrade());
		} else if (this.getGrade() == 1){
			return String.format(this.getCoeff().doubleValue() + "x");
		} else {
			return String.format(this.getCoeff().doubleValue() + "");
		}
	}
}
