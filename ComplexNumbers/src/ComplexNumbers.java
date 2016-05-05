
public class ComplexNumbers {

	public static void main(String[] args) {
		
		//sample
		ComplexNumber first = new ComplexNumber(3, 2);
		ComplexNumber second = new ComplexNumber(4, -3);
		
		ComplexNumber t = first.add(second);
		
		System.out.println(t);
		
		t = first.subtract(second);
		
		System.out.println(t);
		
		t = first.multiply(second);
		
		System.out.println(t);
		
		t = first.divide(second);
		
		System.out.println(t);
	}
	
}
