
public class Mortgage {

	private double downPayment;
	private double mortgageValue;
	private double monthlyPayment;
	private double interestDue;
	private double interestPaid;
	private double interestRate;
	private int durationInYears;
	
	public Mortgage(){}
	
	public double calculateMonthlyPayment() {
		//From https://en.wikipedia.org/wiki/Mortgage_calculator#Monthly_payment_formula
		
		double monthlyPayment;
		//Monthly interest as decimal
		double decInt = (this.interestRate / 12) / 100;
		int durationInMonths = this.durationInYears * 12;
		
		monthlyPayment = 
				(decInt * mortgageValue * Math.pow((1 + decInt), durationInMonths))
				/ (Math.pow((1 + decInt), durationInMonths) - 1);
		
		return monthlyPayment;
		
	}
	
	//Default Getters & Setters, nothing interesting
	public double getinterestPaid() {
		return interestPaid;
	}
	public void setinterestPaid(double interestPaid) {
		this.interestPaid = interestPaid;
	}
	public double getinterestDue() {
		return interestDue;
	}
	public void setinterestDue(double interest) {
		this.interestDue = interest;
	}
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public double getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(double mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	public double getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}
	public int getDurationInYears() {
		return durationInYears;
	}
	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
}
