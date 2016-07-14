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
	
	public void hireNewEmployee(Employee e) {
		roster.put(e.getEmployeeID(), e);
	}
	
	public void fireEmployee(Employee e) {
		roster.remove(e.getEmployeeID());
	}
	
	public void giveEmployeeRaise(Employee e, int newPay) {
		if (roster.containsKey(e.getEmployeeID())) {
			e.setPay(newPay);
		}
	}
	
}
