import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Company {

	//access employee by ID
	private Map<String, Employee> roster;
	
	public Company() {
		roster = new HashMap<String, Employee>();
	}
	
	public Employee getEmployee(String ID) {
		return roster.get(ID);
	}
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> ae = new ArrayList<Employee>();

		for (String s : roster.keySet()) {
			ae.add(roster.get(s));
		}
		
		return ae;
	}
	
	public void payEmployee(Employee e) {
		Employee em = roster.get(e.getEmployeeID());
		
		if (em != null) {
			e.payEmployee(e.getPay());
		}
		else {
			System.out.println("No such employee in the system.");
		}
	}
	
	public void hireNewEmployee(Employee e) {
		roster.put(e.getEmployeeID(), e);
	}
	
	public void fireEmployee(Employee e) {
		roster.remove(e.getEmployeeID());
	}
	
	public void changeEmployeeType(Employee e, Class<? extends Employee> newEmployeeRank) {
		Employee em = e;
		
		try {
			em = newEmployeeRank.cast(em);
		
		} catch(ClassCastException cce) {
			System.out.println("This is not a valid employee rank!");
		}
	}
	
	public void giveEmployeeRaise(Employee e, int newPay) {
		if (roster.containsKey(e.getEmployeeID())) {
			e.setPay(newPay);
			System.out.printf("Employee %s has been given a raise, their pay is now %d%n", e.getEmployeeID(), e.getPay());
		}
	}
	
}
