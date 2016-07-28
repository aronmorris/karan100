import java.util.concurrent.TimeUnit;


public class SalariedEmployee extends Employee {

	public SalariedEmployee(int pay, String ID) {
		super(pay, ID);
	}

	@Override
	public void setEmployeeID(String newID) {
		this.ID = newID;

	}

	@Override
	public String getEmployeeID() {
		return this.ID;
	}

	/**
	 * Salary should be set as an annual value
	 */
	@Override
	public void setPay(int newPay) {
		this.pay = newPay;

	}

	@Override
	public int getPay() {
		return this.pay;
	}

	
	public void payEmployee(int pay) {
		super.setPaySchedule(pay, TimeUnit.DAYS);
		
	}

}
