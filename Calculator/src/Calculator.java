import java.util.Scanner;



/**
 *  Calculator class, solves equations entered into it
 *  TODO: UI?
 * @author Aron
 *
 */
public class Calculator {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(System.in)) {
		
			double firstVal, secondVal;
			String operator;
			
			System.out.printf("Enter the first number.%n");
			firstVal =  validateInput(sc);
			System.out.printf("Now enter the second one.%n");
			secondVal = validateInput(sc);
			System.out.printf("Please input the operator to be used on the values.%n");
			do {
				operator = sc.next();
				if (operator.length() > 1) {
					System.out.printf("Not a valid operator. Valid operators are *, /, -, +%n");
				}
				else {
					switch(operator) {
					case "+": easyPrint(firstVal + secondVal);
						break;
					case "-": easyPrint(firstVal - secondVal);
						break;
					case "*": easyPrint(firstVal * secondVal);
						break;
					case "/": if (secondVal == 0) { 
							System.out.println("Illegal!");
							break; }
							easyPrint(firstVal / secondVal);
						break;
					}
					break;
				}
			} while (true);
			
			//TODO implement shunting yard algorithm?
			
		}
		
	}
	
	public static void easyPrint(double expression) {
		System.out.printf("%.3f%n",expression);
	}
	
	//Returns only valid integers from user input
	public static double validateInput(Scanner sc) {
		double returnVal;
		
		do {
			try {
				returnVal = Double.parseDouble(sc.next());
				break;
			} catch(Exception e) {
				System.out.printf("Invalid input.%n");
			}
		} while(true);
		
		return returnVal;
		
	}
	
}
