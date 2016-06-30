import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.whois.WhoisClient;


public class WhoisSearch {

	public static void main(String[] args) {
		
		WhoisClient wClient = new WhoisClient();
		
		StringBuilder sb = new StringBuilder();
		
		try {
			
			wClient.connect(WhoisClient.DEFAULT_HOST);
	
			String data = wClient.query("=" + args[0]);
			
			sb.append(data);
			
			wClient.disconnect();
			
		} catch (SocketException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		
	}
	
}
