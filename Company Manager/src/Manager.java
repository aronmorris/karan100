import java.util.concurrent.TimeUnit;


public class Manager extends Employee {

	public Manager(int pay, String ID) {
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
