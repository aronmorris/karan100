
/**
 * Abstract superclass for all employee types
 * @author Aron
 *
 */
public abstract class Employee {

	private int pay;
	
	private String ID;
	
	public Employee(int pay, String ID) { 
		this.pay = pay;
		this.ID = ID;
	}
	
	public abstract void setEmployeeID(String newID);
	
	public abstract String getEmployeeID();
	
	public abstract void setPay(int newPay);
	
	public abstract int getPay();
	
}
