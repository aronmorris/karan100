import java.io.File;

public class InvertedIndex {

	private static IndexMap indexMap;
	
	public static void main(String[] args) {
		
	}
	
	public static void begin() {
		indexMap = IndexMap.getIndexMap();
	}
	
	public static void end() {
		indexMap.end();
	}
	
	public static String[] tokenize(String input) {
		String[] retStr = input.split("\\s+"); //splits by any and all whitespace
		return retStr;
	}
	
	public static void index(String[] strArr, File doc, File index) {
		
	}
	
}
