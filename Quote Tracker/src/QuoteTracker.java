import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuoteTracker {
	
	private final static String DEFAULT_URL = "http://finance.yahoo.com/webservice/v1/symbols/INSERT/quote?format=json&view=detail";
	private final static String INSERT = "INSERT";
	
	private final static String LIST = "list";
	private final static String RESOURCES = "resources";
	
	public static void main(String[] args) {
		
		try {

			Scanner sc = new Scanner(System.in);
			
			System.out.println("Type -1 to stop entering stock symbols.");
			
			String title = "";
			ArrayList<String> symbols = new ArrayList<String>();
			
			while(true) {
				title = sc.next();
				if (title.equals("-1")) {
					break;
				}
				else {
					symbols.add(title);
				}
			}
			
			String[] strArr = new String[symbols.size()];
			
			JSONObject json = readJsonFromUrl(generateURL(symbols.toArray(strArr)));
			
			//System.out.println(json.getJSONObject(LIST).getJSONArray(RESOURCES).toString(2));
			
			JSONArray jsonArr = json.getJSONObject(LIST).getJSONArray(RESOURCES);
			
			for (int i = 0; i < jsonArr.length(); i++) {
				  System.out.println(jsonArr.getJSONObject(i).getJSONObject("resource").getJSONObject("fields").get("name"));
				}

			//TODO iterate the returned JSONArray's JSONObjects for name, price, day high, and change
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static String generateURL(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < args.length; i++) {
			sb.append((i == args.length - 1) ? args[i] : args[i] + ",");
		}
		
		String symbols = sb.toString().toUpperCase(); //uppercase insurance
		
		String sURL = DEFAULT_URL.replaceAll(INSERT, symbols);
		
		return sURL;
	}
	//borrowed JSON read from URL from http://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }

}
