
public abstract class Account {

	protected int value;
	private final String PIN;
	
	public Account(String newPIN) {
		PIN = newPIN;
	}
	
	public boolean pinValid(String pin) {
		if (pin.equals(PIN)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getAmount() {
		return value;
	}
	
	public int deposit(int amount) {
		value += amount;
		return value;
	}
	
	public int withdraw(int amount) {
		value -= amount;
		return value;
	}
	
}
