import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class WordCounter {

	public static void main(String[] args) {
		System.out.println(countWords("Hello world, how are you? I'm hello well yes how you.").toString());
	}
	
	public static HashMap<String, Integer> countWords(String input) {
		String[] in = input.split("[^\\w']+"); //split based on punctuation & whitespace, doesn't count apostrophe
		ArrayList<String> arr = new ArrayList<String>(Arrays.asList(in));
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		
		for (int i = 0; i < in.length; i++) {
			counts.put(in[i], Collections.frequency(arr, in[i]));
		}
		
		return counts;
	}
	
}
