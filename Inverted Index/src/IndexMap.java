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
	public static String get(String token) {
		StringBuilder sb = new StringBuilder();
		for (File key : index.keySet()) {
			int count = index.get(key).get(token);
			sb.append("Term \'" + token + "\' appears in ");
			sb.append(key.getName());
			sb.append(" " + count + (count > 1 ? " times." : " time."));
		}
		
		return sb.toString();
		
	}
	
	//TODO fix these here
	public static boolean addToken(String token, File file) {
		
		if (index.containsKey(file)) {
	
			if (index.get(file).containsKey(token)) {
				int tmp = index.get(file).get(token);
				index.get(file).put(token, tmp + 1);
			}
			else {
				index.get(file).put(token, 1);
				
			}
			
		}
		
		else {
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put(token, 1);
			index.put(file, tmp);
		}
		
		return true;
		
	}
	
	public static boolean removeToken(String token) {
		return false;
	}
	
	
	
}
