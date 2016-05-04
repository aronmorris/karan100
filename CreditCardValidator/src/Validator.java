import java.util.ArrayList;
import java.util.Scanner;


/**
 * Uses the Luhn algorithm
 * @author Aron
 *
 */
public class Validator {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a credit card number without spaces to see if it passes a Luhn checksum test.");
		System.out.println("This programs returns as true if the test is passed, and false otherwise.");
		System.out.println("Don't worry, nothing will be stolen.");
		
		boolean passed = false;
		String input;
		
		do {
			
		input = sc.next();
		
		if (!input.matches("[a-zA-Z]+")) passed = true;
		
		} while (!passed);
		
		System.out.println(luhnChecksum(luhnChecksumArr(digitsToArray(input))));
		
		sc.close();
		
	}
	
	public static int[] digitsToArray(String number) {
		String strArr[] = number.split("");
		int[] output = new int[strArr.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = Integer.parseInt(strArr[i]);
		}
		return output;
	}
	
	public static ArrayList<Integer> luhnChecksumArr(int... number) {
		int digits[] = number;
		int sum = 0, checkDigit;
		
		ArrayList<Integer> alNum = new ArrayList<Integer>();
		
		//doubles every odd-indexed value, subtracts 9 if the result is higher than 9
		for (int i = 1; i < digits.length; i += 2) {
			if (digits[i] * 2 > 9) {
				digits[i] = digits[i] * 2 - 9;
			}
			else {
				digits[i] = digits[i] * 2;
			}
		}
		
		for (int j : digits) {
			sum += j;
			alNum.add(j);
		}
		
		checkDigit = (sum * 9) % 10;
		alNum.add(checkDigit);
		
		//ensuring correct values System.out.println(alNum);
		
		return alNum;
		
	}
	
	public static boolean luhnChecksum(ArrayList<Integer> num) {
		
		ArrayList<Integer> cNum = num;
		int sum = 0;
		
		for (int i = 1; i < cNum.size(); i += 2) {
			int j = cNum.get(i);
			if (j > 9) {
				j -= 9;
			}
			cNum.set(i, j);
			
		}
		
		
		
		for (int k : cNum) {
			sum += k;
		}
		
		return (sum % 10 == 0);
	}
	
}
