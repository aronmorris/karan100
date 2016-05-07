
public class SpelledNumbers {
	
	final static String[] to19 = {"", "one", "two", "three", "four", "five", "six", "seven","eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	
	final static String[] tensFrom20 = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
	final static String[] hundredsUp = {"hundred", "thousand", "million", "billion", };
	
	public static void main(String[] args) {
		System.out.println(convertAbove100(13));
	}
	
	/**
	 * Converts values above 100, returns a completed string
	 * @param i
	 * @return
	 */
	private static String convertAbove100(int i) {
		int[] digitGroup = new int[4]; //4 groups of 3 digits for going up to billions (100 000 000 000)
		int pos = Math.abs(i);
		
		String retVal = "";
		
		String[] textGroup = new String[4]; // ^
		
		for (int j = 0; j < digitGroup.length; j++) {
			digitGroup[j] = pos % 1000;
			pos /= 1000;
		}
		
		for (int j = 0; j < digitGroup.length; j++) {
			textGroup[j] = dGroupToStr(digitGroup[j]);
		}
		
		retVal = textGroup[0];
		
		for (int j = 1; j < digitGroup.length; j++) {
			if (digitGroup[j] != 0) {
				String prefix = textGroup[j] + " " + hundredsUp[j] + ", ";
				retVal = prefix + retVal;
			}
		
		}
		
		if (i < 0) {
			retVal = "Negative " + retVal;
		}
		
		return retVal;
	}
	
	private static String dGroupToStr(int i) {
		String retVal = "";
		int hundreds = i / 100; //extract the hundreds value
		int tens = i % 100;
		
		//special case when the input is zero
		if (i == 0) {
			return "zero";
		}
		
		if (hundreds != 0) {
			retVal += convertBelow100(hundreds) + " hundred ";
		}
		
		if (tens != 0) {
			retVal += convertBelow100(tens);
		}
		return retVal;
	}

	/**
	 * Converts values exclusively <100 into English
	 * @param i int value below 100 to be translated to English
	 * @return
	 */
	private static String convertBelow100(int i) {
		String retVal = ""; //string to be returned, is appended with the resulting strings from conversion
		
		int less20Key = (i < 20) ? i : (i % 10);
		int tensKey = i / 10 - 2;
		
		if (i >= 20) {
			retVal += tensFrom20[tensKey] + " "; //trailing space in case of lower digits
		}
		
		
		retVal += to19[less20Key]; //returns the value of i for any value below 20 accurately
		
		
		return retVal;
	}
	
}
