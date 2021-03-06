
public class CheckingAccount extends Account {

	public CheckingAccount(int amount, String PIN) {
		super(PIN);
		value = amount;
	}

	@Override
	public int withdraw(int amount) {
		if (!(value < 0)) {
			value -= amount;
			return value;
		}
		else {
			System.out.println("Account overdrawn, no withdrawals allowed!");
			return 0;
		}
	}

}
