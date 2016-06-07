import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class WordCounter {

	public static void main(String[] args) {
		System.out.println(countWords("Hello world, how are you? I'm hello well yes how you.").toString());
		
		File file = new File("/Users/Aron/Desktop/bar.txt");
		
		try {
			System.out.println(countWords(file).toString());
		} catch(NullPointerException e) {
			System.out.println("Bad path");
			e.printStackTrace();
		}
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
	
	public static HashMap<String, Integer> countWords(File file) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			String built = sb.toString();
			
			return countWords(built);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
