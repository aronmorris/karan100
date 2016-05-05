
public class FactorialFinder {

	public static void main(String[] args) {
		int out = recursiveFactorial(6);
		System.out.println(out);
		System.out.println(iterativeFactorial(6));
	}
	
	public static int recursiveFactorial(int val) {
		if (val == 0) {
			return 1;
		}
		else  {
			return (val * recursiveFactorial(val - 1));
		}
	}
	
	public static int iterativeFactorial(int val) {
		int n = 1;
		for (int i = 1; i <= val; i++) {
			n *= i;
		}
		return n;
	}
	
}
