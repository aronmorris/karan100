import java.util.TimerTask;


public class BusinessAccount extends Account {

	private int creditLimit;

	public BusinessAccount(int amount, String PIN, int creditLimit) {
		super(PIN);
		value = amount;
		this.creditLimit = creditLimit;
	}
	
	@Override
	public int withdraw(int amount) {
		if (value - amount >= creditLimit) {
			value -= amount;
			return amount;
		}
		else {
			System.out.println("You have maxed out your credit.");
			return 0;
		}
	}
	
	public void setCreditLimit(int amount) {
		creditLimit = amount;
	}
	
	public int getCreditLimit() {
		return creditLimit;
	}
	
	class DebtHandler extends TimerTask {
		private int owed;
		
		private final int INTEREST = 20;
		
		public DebtHandler(int amountOwed) {
			owed = amountOwed;
		}
		
		public int getAmountOwed() {
			return owed;
		}
		
		//if more is paid than is owed, overflow gets treated as a regular deposit
		public int payOwed(int amount) {
			if (owed - amount < 0) {
				amount = amount - owed;
				owed = 0;
				deposit(amount);
				return 0;
			}
			else {
				owed -= amount;
				return owed;
			}
		}
		
		public void run() {
			try {
				if (owed > 0) {
					value = (100 * value - value * INTEREST) / 100; //value multiplied by 100 less the interest owed on it
					
				}
			} catch (Exception e) {
				System.out.println("DebtHandler error: " + e.getMessage());
			}
		}
	}

}
