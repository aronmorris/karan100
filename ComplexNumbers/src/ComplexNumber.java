
public class ComplexNumber {
	private double a, b;
	
	private final String i = "i";
	
	public ComplexNumber() {
		this.a = 0;
		this.b = 0;
	}
	
	public ComplexNumber(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	//adds two complexes
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.a + c.a, this.b + c.b);
	}
	//distributes negative and adds as normal
	public ComplexNumber subtract(ComplexNumber c) {
		double nCA = -c.a;
		double nCB = -c.b;
		return this.add(new ComplexNumber(nCA, nCB));
	}
	
	//multiplies through FOIL
	public ComplexNumber multiply(ComplexNumber c) {
		return new ComplexNumber(this.a * c.a - this.b * c.b,
								 this.a * c.b + this.b * c.a);
	}
	
	//Performs division
	public ComplexNumber divide(ComplexNumber c) {
		double den = Math.pow(c.mod(), 2);
        return new ComplexNumber((this.a * c.a + this.b * c.b) / den,
        						 (this.b * c.a - this.a * c.b) / den);
		
	}
	
	private double mod() {
        if (this.a != 0 || this.b != 0) {
            return Math.sqrt(this.a * this.a + this.b *this.b);
        } else {
            return 0;
        }
	}
	
	@Override
	public String toString() {
		String operator = "";
		
		if (this.b > 0) {
			operator = "+";
		}
		
		else if ((this.b) < 0.01 && (this.b) >-0.01) { //thresholding
			return Double.toString((this.a));
		}
		
		return (this.a) + operator + (this.b) + i;
		
	}
	
	
}
