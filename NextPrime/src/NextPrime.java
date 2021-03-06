import java.util.LinkedList;
import java.util.Scanner;

//Lists each prime until asked to stop
public class NextPrime {

	static LinkedList<Integer> capturedPrimes = new LinkedList<Integer>();
	
	public static void main(String[] args) {
		
		System.out.println("This program will list primes until receiving the input -1."
				+ "\n" + "Give it any other integer value for it to go to the next prime.");
		
		Scanner sc = new Scanner(System.in);
		
		capturedPrimes.add(1);
		capturedPrimes.add(2);
		
		int current = 3;
		int lastval = capturedPrimes.getLast();
		
		while(true) {
			
			returnPrime(current);
			
			current++;
			
			if (capturedPrimes.getLast() != lastval) {
				System.out.printf("GOT ONE! %d%n", capturedPrimes.getLast());
				lastval = capturedPrimes.getLast();
				
				int n = sc.nextInt();
				
				if (n == -1) {
					break;
				
				}
			}
			/**Sleeping for faster run for debug until user input required
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			**/
		}
		
		sc.close();
		
	}
	
	static void returnPrime(int n) {
		
		//Reuse code from PrimeFactor
		n = (int) Math.sqrt(n);
		
		
		//Divide until value is no longer even
		while (n % 2 == 0) {
			//System.out.printf("%d ", 2);
			n = n / 2;
		}
		
		//Divide by every value until it can't divide by it further if the value is a divisor
		//While also not using any even values since they've been eliminated
		for (int i = 3; i < n; i += 2) {
			while (n % i == 0) {
				//System.out.printf("%d ", 2);
				n = n / i;
			}
		}
		
		capturePrimes(n);
		
	}
	
	static void capturePrimes(int n ) {
		if ((!capturedPrimes.contains(n)) && n % 2 != 0) {
			capturedPrimes.add(n);
		}
	}
	
}
