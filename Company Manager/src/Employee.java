import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Abstract superclass for all employee types
 * @author Aron
 *
 */
public abstract class Employee implements Runnable {

	protected int pay;
	
	protected String ID;
	
	public Employee(int pay, String ID) { 
		this.pay = pay;
		this.ID = ID;
	}
	
	public abstract void setEmployeeID(String newID);
	
	public abstract String getEmployeeID();
	
	public abstract void setPay(int newPay);
	
	public abstract int getPay();
	
	public void payEmployee(int pay) {
		System.out.printf("Employee %s has been paid $d.%n", this.getEmployeeID(), this.getPay());
	}

	public void run() {
		payEmployee(this.getPay());
	}
	
	public void setPaySchedule(int numberUnits, TimeUnit unit) {
		
		System.out.println("Establishing pay schedule for " + this.getEmployeeID());
		
		//0 here means immediate pay, then pay every number of units per unit
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		 scheduler.scheduleAtFixedRate(this, 1000, numberUnits, unit);
		
	}
	
	
	
}
