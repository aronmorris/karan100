/**
 * Using https://en.wikipedia.org/wiki/Exponentiation_by_squaring as a reference
 * @author Aron
 *
 */
public class FastExponentiation {
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		int exponentiated = exponentiate(2, 10);
		long stop = System.nanoTime();
		
		System.out.printf("Result of a^b using fast exponentiation: %d%n"
				+ "Time elapsed: %d nanoseconds%n", exponentiated, (stop - start));

		
		start = System.nanoTime();
		exponentiated = (int) Math.pow(2, 10);
		stop = System.nanoTime();
		
		System.out.printf("Result of a^b using Math.pow: %d%n"
						+ "Time elapsed: %d nanoseconds%n", exponentiated, (stop - start));
		
	}
	
	//a is exponand, b is exponent
	public static int exponentiate(int a, int b) {
		if (b == 1) {
			return a;
		}
		if (b == 2) {
			return a * a;
		}
		
		if (b % 2 == 0) {
			return exponentiate(exponentiate(a, b / 2), 2);
		}
		
		else {
			return a * exponentiate(exponentiate(a, (b - 1) / 2), 2);
		}
	}
	
}
