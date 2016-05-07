
public class SpelledNumbers {
	
	final static String[] to19 = {"", "one", "two", "three", "four", "five", "six", "seven","eight", "nine", "ten",
			"eleven", "twelve", "thirten", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	
	final static String[] tensFrom20 = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
	final static String[] hundredsUp = {"", "hundred", "thousand", "million", "billion", };
	
	public static void main(String[] args) {
		System.out.println(convertBelow100(23));
	}
	
	private static String convertBelow100(int i) {
		String retVal = "";
		
		int less20Key = (i < 20) ? i : (i % 10);
		int tensKey = i / 10 - 2;
		
		if (i >= 20) {
			retVal += tensFrom20[tensKey] + " "; //trailing space in case of lower digits
		}
		
		
		retVal += to19[less20Key]; //returns the value of i for any value below 20 accurately
		
		
		return retVal; //TODO REMOVE THIS IDIOT
	}
	
}
