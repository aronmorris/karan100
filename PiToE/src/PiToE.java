import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class PiToE {
	
	public static void main(String args[]) {
		
		System.out.println(piToE(100));
		
	}
	
	public static String piToE(int precision) {
		BigDecimal e = BigDecimal.ONE; //e
		BigDecimal f = BigDecimal.ONE; //factorial value
		
		for(int i = 1; i <= precision ; i++) {
			
			  f = f.multiply(new BigDecimal(i));

			  e = e.add(BigDecimal.ONE.divide(f, new MathContext(2000, RoundingMode.HALF_UP)));
			}
		
		
		return e.toString().substring(0, precision - 1);
	}
	
}
