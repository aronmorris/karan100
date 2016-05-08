import java.math.BigDecimal;


public class PiToN {

	public static void main(String[] args) {
		System.out.println(getPiToN(5));
	}
	
	public static String getPiToN(int n) {
		
		BigDecimal pi = new BigDecimal(0);	
		
		double b_16 = 1;
		
		pi.setScale(n);
		//Bailey–Borwein–Plouffe formula from https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
		for (int i= 0; i <= n; i++){
			pi = pi.add(new BigDecimal(
					1.0 / b_16 * (4.0 / (8 * i + 1) - 2.0 / (8 * i + 4) - 1.0 / (8 * i + 5) - 1.0 / (8 * i + 6))));
			b_16 *= 16;
		}		
		return pi.toString().substring(0, n+2); 
		//precision counts "3." as part of the string, adding 2 gives it the required decimal precision
	}
	
}
