
public class Main {

	public static void main(String[] args) {
		
		for (int i = 1; i <= 4; i++) {
			double res = 0.00f;
			
			res = (Math.sqrt(i) + 2)/(2 * i);
			
			System.out.println(res);
		}
		
		System.out.println("s = 9:\n");
		
		for (int i = 1; i <= 4; i++) {
			double res = 0.00f;
			
			res = (Math.sqrt(i) + 9)/(2 * i);
			
			System.out.println(res);
		}
		
	}
	
}
