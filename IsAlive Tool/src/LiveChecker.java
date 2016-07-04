import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class LiveChecker {

	static final int ECHO_PORT = 7;
	
	public static void main(String[] args) {
		
		String url = args[0].contains("http://") ? args[0] : "http://" + args[0]; //append protocol to ensure httpurl works
		
		int timeout = Integer.parseInt(args[1]);
		
		try {
			
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			
			connection.setConnectTimeout(timeout);
			
			connection.setReadTimeout(timeout);
			
			connection.setRequestMethod("HEAD");
			
			int response = connection.getResponseCode(); //200-399 ok
			
			if (200 <= response && response <= 399) {
				System.out.println(response + " ok");
			}
			
		} catch (IOException e) {
			System.out.println("HTTP code not in range 200-399 KO");
			e.printStackTrace();
		}
		
	}
	
}
