import java.math.BigDecimal;
import java.util.Scanner;


public class TaxCalculator {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		double input = -1;
		double tax = -1;
		boolean valid = false;
		
		do {
			System.out.println("Please enter a valid sum of money.");
			try {
				double container = Double.parseDouble(sc.next());
				if (container < 0) {
					throw new NumberFormatException();
				}
				input = container;
			} catch (NumberFormatException e) {
				System.out.println("Not a valid value!");
			} catch(NullPointerException e) {
				System.out.println("Must enter a value!");
			}
			System.out.println("Now, please enter the tax rate as a whole number.");
			try {
				double container = Double.parseDouble(sc.next());
				if (container < 0) {
					throw new NumberFormatException();
				}
				tax = container;
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("Not a valid value!");
			} catch(NullPointerException e) {
				System.out.println("Must enter a value!");
			}
			
		} while(!valid);
		
		System.out.println(input);
		
		sc.close();
		
		System.out.printf("Your total with tax is: %.2f%n", taxedAmount(input, tax));
		
	}
	
	public static double taxedAmount(double amount, double taxRate) {
		double returnVal = (amount * taxRate / 100) + amount;
		return returnVal;
	}
	
}
