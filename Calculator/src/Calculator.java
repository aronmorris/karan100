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
		
			int firstVal, secondVal;
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
					break;
				}
			} while (true);
			
			//TODO implement shunting yard algorithm so any equation can be solved
			
			
			
		}
		
	}
	
	//Returns only valid integers from user input
	public static int validateInput(Scanner sc) {
		int returnVal;
		
		do {
			try {
				returnVal = Integer.parseInt(sc.next());
				break;
			} catch(Exception e) {
				System.out.printf("Invalid input.%n");
			}
		} while(true);
		
		return returnVal;
		
	}
	
}
