import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Returns the change for a transaction in quarters, dimes, nickels, and pennies,
 * given a cost and amount paid.
 * @author Aron
 *
 */
public class ChangeReturn {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		double price, payment;
		
		boolean transactionContinue = true;
		
		while(transactionContinue) {
		
			System.out.printf("What is the price of the item?%n");
			
			price = doubleInputValidator(sc);
			
			System.out.printf("The cost is %.2f, how much are you paying for it?%n", price);
			
			payment = doubleInputValidator(sc);
			
			System.out.printf("The cost is %.2f, and the payment is %.2f.%n", price, payment);
		
			if (payment < price) {
				System.out.printf("You've paid too little!%n");
			}
			
			else if (payment == price) {
				System.out.printf("You've paid in exact change! Good on you!%n");
			}
			
			else {
				int remaining = (int)Math.round((payment - price) * 100);
		
				int quarter = 25, qCount = 0, dime = 10, dCount = 0, nickel = 5, nCount = 0, penny = 1, pCount = 0;
				
				while (remaining > 0) {
					if (remaining - quarter >= 0) {
						remaining = remainder(remaining, quarter);
						qCount++;
					}
					else if (remaining - dime >= 0) {
						remaining = remainder(remaining, dime);
						dCount++;
					}
					else if (remaining - nickel >= 0) {
						remaining = remainder(remaining, nickel);
						nCount++;
					}
					else if (remaining - penny >= 0) {
						remaining = remainder(remaining, penny);
						pCount++;
					}
				}
				
				System.out.printf("Your change is %d quarter(s), %d dime(s), %d nickel(s), and %d pennies.%n",
						qCount, dCount, nCount, pCount);
				 
			}
			
			System.out.printf("Do you want to calculate another transaction?%n");
			String answer = sc.next();
			if (!(answer.matches("(?i)yes|y"))) {
				transactionContinue = false;
			}
		}
		
	}
	
	public static int remainder(int sum, int coin) {
		
		
		
		if (sum - coin >= 0) {
			sum -= coin;
		}
		
		return sum;
	}
	
	//Ensures only valid input of type double is accepted
	//Writing multiple try/catches for one thing is lame
	public static double doubleInputValidator(Scanner sc) {
		double output = -1;
		boolean valid = false;
		
		while(!valid) {
			
			try {
				output = sc.nextDouble();
				valid = true;
			} catch (InputMismatchException e) {
				System.out.printf("Invalid input! Must be a price.%n");
				sc.next(); //consume invalid input
			}
			
		}
		
		return output;
	}
	
}
