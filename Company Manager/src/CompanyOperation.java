import java.util.ArrayList;


public class CompanyOperation {

	public static void main(String[] args) {
		
		Company apple = new Company();
		
		Employee x = new Executive(200, "Steve Jobs");
		
		apple.hireNewEmployee(x);
		
		ArrayList<Employee> yep = apple.getAllEmployees();
		
		for (Employee e : yep) {
			System.out.println(e.getEmployeeID());
			
		}
		System.out.println("Done!");
		
		apple.payEmployee(apple.getEmployee(x.ID));
		
		apple.changeEmployeeType(apple.getEmployee("Steve Jobs"), "hourly");
		
		apple.giveEmployeeRaise(x, 400);
		
		
		
	}
	
}
