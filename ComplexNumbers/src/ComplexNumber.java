
public class ComplexNumber {
	private int a, b;
	
	private final String i = "i";
	
	public ComplexNumber() {
		this.a = 0;
		this.b = 0;
	}
	
	public ComplexNumber(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(this.a + c.a, this.b + c.b);
	}
	
	public ComplexNumber multiply(ComplexNumber c) {
		int outA = this.a * c.a + this.a * c.b;
		int outB = this.b * c.a + this.b * c.b;
		return new ComplexNumber(outA, outB);
	}

	@Override
	public String toString() {
		String operator = "";
		
		if (b > 0) {
			operator = "+";
		}
		
		else if (b == 0) {
			return Integer.toString(this.a);
		}
		
		return this.a + operator + this.b + i;
		
	}
	
}
