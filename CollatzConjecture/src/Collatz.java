
public class Collatz {

	public static void main(String[] args) {
		System.out.printf("Steps to reach 1, not counting the input as a step: %d%n", stepsToReachOne(6));
	}
	
	public static int stepsToReachOne(int startingVal) {
		int ctr = 0;
		
		while (startingVal != 1) {
			if (startingVal <= 1) {
				break;
			}
			ctr++;
			
			if (startingVal % 2 == 0) {
				startingVal /= 2;
			}
			
			else {
				startingVal = startingVal * 3 + 1;
			}
			System.out.println(startingVal);
		}
		return ctr;
		
	}
	
}
