import java.util.concurrent.TimeUnit;


public class HourlyEmployee extends Employee {

	public HourlyEmployee(int pay, String ID) {
		super(pay, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setEmployeeID(String newID) {
		

	}

	@Override
	public String getEmployeeID() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Pay int should be set as an hourly value
	 */
	@Override
	public void setPay(int newPay) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPay() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void payEmployee(int hoursWorked) {
		setPaySchedule(hoursWorked, TimeUnit.HOURS);
		
	}

}
