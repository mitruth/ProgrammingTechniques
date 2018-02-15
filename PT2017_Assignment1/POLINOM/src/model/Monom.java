package model;
public abstract class Monom {

	protected int grade;
	protected Number coeff;//I have chosen this data type because both Integer and Double are subclasses of Number
// I have written getter and setter for the degree here because they are the same for both subclasses
	public int getGrade() {
		return this.grade;
	}
	
	public void setGrade(int g) {
		this.grade = g;
	}
	//following here are all the methods that are going to be implemented in the subclasses
	public abstract Number getCoeff();
	
	public abstract void setCoeff(Number c);
	
	public abstract String toString();
	public abstract Monom add (Monom m);
	public abstract Monom subtract (Monom m);
	public abstract Monom multiply (Monom m);
	public abstract Monom derive ();
	public abstract Monom integrate();
}