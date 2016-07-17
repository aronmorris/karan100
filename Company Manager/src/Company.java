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
	
	/**
	 * Valids are hourly, salary, manager, executive
	 * @param e
	 * @param rank
	 */
	public void changeEmployeeType(Employee e, String rank) {
		Employee em;
		
		switch(rank.toLowerCase()) {
		case "hourly": em = new HourlyEmployee(e.pay, e.ID);
		break;
		case "salary": em = new SalariedEmployee(e.pay, e.ID);
		break;
		case "manager": em = new Manager(e.pay, e.ID);
		break;
		case "executive": em = new Executive(e.pay, e.ID);
		break;
		default: em = null;
		break;	
		}
		
		if (em != null) {
			roster.put(em.ID, em);
		} else {
			System.out.println("Not an employee rank. Valid ranks are hourly, salary, manager, executive");
		}
		
	}
	
	public void giveEmployeeRaise(Employee e, int newPay) {
		if (roster.containsKey(e.getEmployeeID())) {
			e.setPay(newPay);
			System.out.printf("Employee %s has been given a raise, their pay is now %d%n", e.getEmployeeID(), e.getPay());
		}
	}
	
}
