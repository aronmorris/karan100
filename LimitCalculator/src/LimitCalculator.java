public class LimitCalculator {

	public static void main(String[] args) {
		
		Limit lc = new Limit(x -> x = x + 2);
		
		
		System.out.println(lc.limit(5, "above"));
			
	}
	
}
