
public class Eratosthenes {

	public static void main(String[] args) {
		sieve(2000);
	}
	
	public static void sieve(int n) {
		
		boolean[] A = new boolean[n];
		
		for (int i = 2; i < n; i++) {
			A[i] = true;
		}
		
		for (int i = 0; i <= Math.sqrt(A.length); i++) {
			if (A[i]) {
				int ctr = 0;
				for (int j = i * i; j < n; j = (i * i) + (i * ctr)) {
					A[j] = false;
					ctr++;
				}
			}
		}
		
		int ctr = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i]) {
				if (ctr % 10 == 0) {
					System.out.printf("%d,%n", i);
				}
				else if (i == A.length - 1){
					System.out.printf("%d. ", i);
				}
				else {
					System.out.printf("%d, ", i);
				}
				ctr++;
			}
		}
	}
	
}
