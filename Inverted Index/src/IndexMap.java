import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class IndexMap {

	private static HashMap<String, ArrayList<String>> index; 
	private static HashMap<String, Integer> occurrences;
	
	public IndexMap() {
		
		index = new HashMap<String, ArrayList<String>>();
		occurrences = new HashMap<String, Integer>();
		
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
	public static Integer get(String token) {
		return occurrences.get(token);
	}
	
	//TODO fix these here
	public static boolean addToken(String token) {
		
		if (occurrences.containsKey(token)) {
			int count = occurrences.get(token);
			occurrences.put(token, count);
		} else {
			occurrences.put(token, 1); //add to occurrence index	
		}
		
		return true;
		
	}
	
	public static boolean removeToken(String token) {
		return false;
	}
	
	
	
}
