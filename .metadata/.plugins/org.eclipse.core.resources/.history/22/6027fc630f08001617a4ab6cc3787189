import java.util.Scanner;

public class MortgageCalculator {

	/** Calculates the monthly payments of a fixed term mortgage over Nth terms at
	 *  a given interest rate. Also informs duration to pay back the loan entirely.
	 */
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Mortgage mortgage = new Mortgage();
		boolean uncancelled = true;
		
		while(uncancelled) {
		
			System.out.printf("Enter the cost of your new home, please.%n");
			mortgage.setMortgageValue(sc.nextDouble());
			
			System.out.printf("Now enter the duration, in years.%n");
			mortgage.setDurationInYears(sc.nextInt());
			
			System.out.printf("What is the interest rate per month as a whole number?%n");
			mortgage.setInterestRate(sc.nextDouble());
			
			//TODO placeholder, actual formula on wikipedia to be implemented tomorrow
			mortgage.setMonthlyPayment(
					(mortgage.getMortgageValue() / mortgage.getDurationInYears() * 12)
					* (mortgage.getInterestRate() / 100)
					);
			
			System.out.printf("Your monthly interest due is: %.2f%n", mortgage.getMonthlyPayment());
			
		}
		
		sc.close();
		
	}
	
}
