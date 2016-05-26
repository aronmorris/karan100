import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InvertedIndex {

	private static IndexMap indexMap;
	
	public static void main(String[] args) {
		File file = new File("./src/tmp/Text.txt");
		
		//begin();
		
		//System.out.println(IndexMap.get("hello"));
		
		indexMap = new IndexMap();
		
		index(file);
		
		System.out.println(IndexMap.get("hello"));
		
		//end();
		
	}
	
	/*public static void begin() {
		indexMap = IndexMap.getIndexMap();
	}
	*/
	public static void index(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			
			String built = sb.toString();
			
			index(tokenize(built), file);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	/*
	public static void end() {
		IndexMap.end();
	}
	*/
	public static String[] tokenize(String input) {
		input = input.replaceAll("[^a-zA-Z0-9'\\s]+"," ");
		String[] retStr = input.split("\\s+"); //splits by any and all whitespace
		return retStr;
	}
	
	private static void index(String[] strArr, File srcDoc) {
		for (String s : strArr) {
			s = s.toLowerCase();
			IndexMap.addToken(s);
		}
	}
	
}
