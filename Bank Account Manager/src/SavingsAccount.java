
public class SavingsAccount extends Account {

	public SavingsAccount(int amount, String PIN) {
		super(PIN);
		value = amount;
	}
	
	@Override
	public int withdraw(int amount) {
		if (value - amount >= 0) {
			value -= amount;
			return amount;
		} else {
			System.out.println("Unable to withdraw that amount.");
			return 0;
		}
	}

}
