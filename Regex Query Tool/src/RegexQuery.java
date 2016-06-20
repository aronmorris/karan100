import java.util.ArrayList;


public class RegexQuery {
	
	public static ArrayList<String> regexQuery(String query, String regex) {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		for (String str : query.split(" ")) {
			if (str.matches(regex)) {
				arr.add(str);
			}
		}
		
		return arr;
		
	}

}
