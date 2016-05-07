import java.util.Random;


public class CoinFlips {

	public static void main(String[] args) {
		String seq = flipCoin(100);
		System.out.printf("Your sequence of flips is as follows: %s%n", seq);
	}
	
	public static String flipCoin(int i) {
		int flips = Math.abs(i); //can't flip a coin negative times
		int hCount = 0, tCount = 0;
		String sequence = "";
		
		for (int j = 0; j < flips; j++) {
			int flipped = flip();
			//System.out.println(flipped); //debugs
			if (flipped == 0) {
				sequence += "H";
				hCount++;
			}
			else if (flipped == 1) {
				sequence += "T";
				tCount++;
			}
		}
		System.out.printf("You flipped a coin %d times. The results were:%n"
						+ "Heads: %d%n"
						+ "Tails: %d%n", flips, hCount, tCount);
		return sequence;
	}
	
	private static int flip() {
		return new Random().nextInt(2);
	}
	
}
