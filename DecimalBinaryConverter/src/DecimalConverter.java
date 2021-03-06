import java.util.Scanner;


public class DecimalConverter {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String strInput, strOutput;
		
		System.out.printf("Are you converting a binary value or a decimal one?%n");
		System.out.printf("1. Decimal%n"
						+ "2. Binary%n");
		
		do {
			try {
				strInput = sc.next();
				if (strInput.matches("(?i)1|decimal")) {
					System.out.printf("Enter the value to convert.%n");
					strInput = sc.next();
					strOutput = toBinary(Integer.parseInt(strInput));
					System.out.printf("Your converted value is %s", strOutput);
					break;
				}
				else if (strInput.matches("(?i)2|binary")) {
					System.out.printf("Enter the value to convert.%n");
					strInput = sc.next();
					strOutput = String.valueOf(toDecimal(strInput));
					System.out.printf("Your converted value is %s", strOutput);
					break;
				}
				else {
					System.out.printf("Not a valid option!");
				}
				
			} catch (Exception e) {
				System.out.printf("Couldn't parse.%n");
			}
		} while(true);
		
		sc.close();
		
	}
	
	public static String toBinary(int i) {
		return Integer.toBinaryString(i);
	}
	
	public static int toDecimal(String b) {
		return Integer.parseInt(b, 2); //parses binary string in base 2
	}
	
}
