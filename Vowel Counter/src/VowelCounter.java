import java.util.Scanner;


public class VowelCounter {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String vowels = "[aeiouy]";
		String in = sc.nextLine();
		int ctr = 0;
		
		for (String c : in.split("")) {
			if (vowels.contains(c)) {
				ctr++;
			}
		}
		
		System.out.println(ctr);
		
	}
	
}
