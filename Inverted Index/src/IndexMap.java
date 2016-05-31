import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class IndexMap {

	//private static HashMap<String, HashMap<File, ArrayList<String>>> index; 
	private static HashMap<File, HashMap<String, Integer>> index;
	
	public IndexMap() {
		
		//index = new HashMap<String, HashMap<File, ArrayList<String>>>();
		index = new HashMap<File, HashMap<String, Integer>>();
		
		//defeats instantiation
		/*try
	      {
	         FileInputStream fileIn = new FileInputStream("./src/tmp/index.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         instance = (IndexMap) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch(FileNotFoundException f) {
	    	  f.printStackTrace();
	    	  new File("./src/tmp/").mkdirs();
	    	  instance = new IndexMap(-1);
	    	  
	      } catch(IOException i) {
	         i.printStackTrace();
	         return;
	      } catch(ClassNotFoundException c) {
	         System.out.println("IndexMap class not found.");
	         c.printStackTrace();
	         return;
	      }
	      */
	}

	/*
	public static boolean end() {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("./src/tmp/index.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(instance);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /src/tmp/index.ser");
	         return true;
	      } catch(IOException i) {
	          i.printStackTrace();
	          return false;
	      }

	}
	*/
	/*
	public static IndexMap getIndexMap() {
		if (instance == null) {
			instance = new IndexMap();
		}
		return instance;
	}
	*/
	
	/**
	 * Fetches the occurrences of a token for each file it appears in
	 * @param token The string token to be found
	 * @return Returns a hashmap<File, Integer> with each key's value = its occurrences for that file
	 */
	public static HashMap<File, Integer> getOccurrences(String token) {
		
		HashMap<File, Integer> retMap = new HashMap<File, Integer>();
		
		for (File key : index.keySet()) {
			if (index.get(key).containsKey(token)) {
				retMap.put(key, index.get(key).get(token));
			}
		}
		
		return retMap;
		
	}
	

	//TODO add method that returns a sorted list of which file has the closest match to the searched terms
	//TODO add method that searches for multiple terms at once and returns the weighed result from the TODO above
	
	/**
	 * Handles many tokens at once rather than one at a time
	 * For logic see addToken(String token, File File)
	 * @param tokens
	 * @param file
	 * @return
	 */
	public static boolean addToken(String[] tokens, File file) {
		
		for (String token : tokens) {
			addToken(token, file);
		}
		
		return true;
		
	}
	
	/**
	 * Returns true if the index contains the file, false otherwise, adds the individual token
	 * @param token the token to be added
	 * @param file the file containing the token
	 * @return True if the index contains the file already, false otherwise
	 */
	public static boolean addToken(String token, File file) {
		
		if (index.containsKey(file)) {
	
			if (index.get(file).containsKey(token)) {
				int tmp = index.get(file).get(token);
				index.get(file).put(token, tmp + 1);
			}
			else {
				index.get(file).put(token, 1);
				
			}
			
			return true;
			
		}
		
		else {
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put(token, 1);
			index.put(file, tmp);
			
			return false;
			
		}
		
		
	}
	/**
	 * Eliminates all references of a given token in the specified file
	 * @param token Token to be removed
	 * @param file File containing the token
	 * @return Returns true if the token exists and was deleted, false otherwise
	 */
	public static boolean removeToken(String token, File file) {
		
		if (index.containsKey(file)) {
			if (index.get(file).containsKey(token)) {
				index.get(file).remove(token);
				return true;
			}
		}
		
		return false;
		
	}
	
	public void index(File file) {
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
	
	public static String[] tokenize(String input) {
		input = input.replaceAll("[^a-zA-Z0-9'\\s]+"," ");
		String[] retStr = input.split("\\s+"); //splits by any and all whitespace
		return retStr;
	}
	
	private static void index(String[] strArr, File srcDoc) {
		for (String s : strArr) {
			s = s.toLowerCase();
			IndexMap.addToken(s, srcDoc);
		}
	}
	
	
	
}
