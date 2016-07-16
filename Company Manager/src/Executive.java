import java.util.concurrent.TimeUnit;


public class Executive extends Employee {

	public Executive(int pay, String ID) {
		super(pay, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setEmployeeID(String newID) {
		ID = newID;

	}

	@Override
	public String getEmployeeID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

	@Override
	public void setPay(int newPay) {
		pay = newPay;

	}

	@Override
	public int getPay() {
		// TODO Auto-generated method stub
		return this.pay;
	}

	
	public void payEmployee(int bonus) {
		super.setPaySchedule(bonus, TimeUnit.DAYS);
		
	}

}
