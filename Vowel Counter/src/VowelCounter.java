import java.util.HashMap;
import java.util.Scanner;


public class VowelCounter {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String vowels = "[AEIOUY]";
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		int ctr = 0;
		
		String in = sc.nextLine();
		
		for (String c : in.toUpperCase().split("")) {
			if (vowels.contains(c)) {
				ctr = (counts.get(c) == null ? 0 : counts.get(c));
				counts.put(c, ctr + 1);
				ctr = 0;
			}
		}
	
		for (String k : counts.keySet()) {
			System.out.printf("Instances of \"%s\": %d%n", k, counts.get(k));
		}
		
		sc.close();
		
	}
	
}
