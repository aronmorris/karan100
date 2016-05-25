import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class IndexMap implements Serializable {

	private static final long serialVersionUID = 123L;

	private static IndexMap instance = null;
	
	protected IndexMap() {
		//defeats instantiation
		try
	      {
	         FileInputStream fileIn = new FileInputStream("/src/tmp/index.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         instance = (IndexMap) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("IndexMap class not found.");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public static boolean end() {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("/src/tmp/index.ser");
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
	
	public static IndexMap getIndexMap() {
		if (instance == null) {
			instance = new IndexMap();
		}
		return instance;
	}
	
	//TODO fix these here
	public static boolean addToken() {
		return false;
	}
	
	public static boolean removeToken() {
		return false;
	}
	
	
	
}
