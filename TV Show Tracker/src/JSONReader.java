import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;


public class JSONReader {

	private static JSONObject json;
	
	
	public static String getValue(String jsonKey) {
		
		String value = null;
		
		if (json != null) {
			value = json.get(jsonKey).toString();
		}
		
		return value;
		
	}
	
	private static String readAll(Reader rd) throws IOException {
		  StringBuilder sb = new StringBuilder();
		  
		  int pointer;
		  
		  while ((pointer = rd.read()) != -1) {
			  sb.append((char) pointer);
		  }
		
		  return sb.toString();
		  
	}

	private static JSONObject readJsonFromUrl(URL url) throws IOException {
		
		InputStream in = url.openStream();
		
		try ( BufferedReader rd = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")))) {
			String jsonText = readAll(rd);
			
			JSONObject json = new JSONObject(jsonText);
			
			return json;
		} finally {
			in.close();
		}
		
	}
	
}
