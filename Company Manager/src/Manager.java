import java.util.concurrent.TimeUnit;


public class Manager extends Employee {

	public Manager(int pay, String ID) {
		super(pay, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setEmployeeID(String newID) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getEmployeeID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPay(int newPay) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPay() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void payEmployee(int pay) {
		super.setPaySchedule(pay, TimeUnit.DAYS);
		
	}

}