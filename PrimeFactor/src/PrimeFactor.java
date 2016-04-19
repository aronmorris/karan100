import java.util.Arrays;
import java.util.Scanner;


public class PrimeFactor {

	public static void main(String[] args) {
	
		System.out.println("Enter integer to be factorized." + "\n"
				+ "All prime factors will be listed." + "\n" 
				+ "Enter -1 to exit.");
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			int n = sc.nextInt();
			
			if (n == -1) {
				break;
			}
			
			//Divide until value is no longer even
			while (n % 2 == 0) {
				System.out.printf("%d ", 2);
				n = n / 2;
			}
			
			n = (int) Math.sqrt(n);
			
			//Divide by every value until it can't divide by it further if the value is a divisor
			//While also not using any even values since they've been eliminated
			for (int i = 3; i < n; i += 2) {
				while (n % i == 0) {
					System.out.printf("%d ", i);
					n = n / i;
				}
			}
			//In case the remaining value is > 2 it's printed
			if (n > 2) {
				System.out.printf("%d ", n);
			}
			
			System.out.println();
			
		}
		
		sc.close();
		
	}
	
	
	
}
