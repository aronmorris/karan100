
public class PigLatin {

	private final String vowels = "aeiouy";
	
	public static void main(String[] args) {
		System.out.println(atinizeLay("Hello world foo bar test enemy as vowel"));
	}
	
	public static String atinizeLay(String input) {
		
		input = input.toLowerCase();
		String[] words = input.split(" ");
		StringBuilder sb = new StringBuilder();
		
		
		for (String word : words) {
			if (!word.substring(0, 1).matches("[aeiou]")) {
				String first = word.substring(1);
				String second = word.substring(0, 1) + "ay";
				
				sb.append(first + second + " ");
			}
			else {
				sb.append(word + "yay ");
			}
		}
		
		return sb.toString();
		
	}
	
}
