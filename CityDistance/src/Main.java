import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the names of the two cities you want to find the distance between.");
		System.out.println("Please separate them with the enter key.");
		
		String city1, city2;
		
		city1 = sc.next();
		
		city2 = sc.next();
		
		CoordinateCalculator cc = new CoordinateCalculator(city1, city2);
		
		//cc.setFirstCity("Montreal");
		
		//cc.setSecondCity("New York");
		
		cc.query();
		
		sc.close();
		
	}
	
}
