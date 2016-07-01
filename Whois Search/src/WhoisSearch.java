import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.whois.WhoisClient;


public class WhoisSearch {

	public static void main(String[] args) {
		
		System.out.println(whois(args[0]));
		
	}
	
	public static String whois(String domain) {
		
		StringBuilder sb = new StringBuilder();
		
		WhoisClient whois = new WhoisClient();
		
		try {
			whois.connect(WhoisClient.DEFAULT_HOST);
			
			String dat = whois.query("=" + domain);
			
			sb.append(dat);
			
			whois.disconnect();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
		
	}
	
}
