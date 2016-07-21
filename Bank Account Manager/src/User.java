import java.util.HashMap;
import java.util.Map;

enum Accounts {
	CHECKING,
	SAVINGS,
	BUSINESS
}

public class User {
	
	private final String PIN;
	
	private final int DEFAULT_CREDIT_LIMIT = 10000;
	
	protected Map<Accounts, Account> accounts;
	
	public User(String userPIN) {
		accounts = new HashMap<Accounts, Account>();
		PIN = userPIN;
		
	}
	
	public void addAccount(Accounts type, int initialSum) {
		switch(type) {
		case CHECKING: accounts.put(type, new CheckingAccount(initialSum, PIN));
			break;
		case SAVINGS: accounts.put(type, new SavingsAccount(initialSum, PIN));
			break;
		case BUSINESS: accounts.put(type, new BusinessAccount(initialSum, PIN, DEFAULT_CREDIT_LIMIT));
			break;
		default: System.out.println("Account could not be added.");
			break;
		}
	}
	
	public Account accessAccount(Accounts type) {
		if (accounts.containsKey(type)) {
			return accounts.get(type);
		}
		System.out.println("This user doesn't have this type of account.");
		throw new NullPointerException();
	}
	
}
