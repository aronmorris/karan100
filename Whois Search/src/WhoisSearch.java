import java.io.IOException;
import java.net.SocketException;
<<<<<<< HEAD
=======
import java.util.regex.Matcher;
import java.util.regex.Pattern;
>>>>>>> c16d833c6c575cda901ba7b0c57998016b11d7ef

import org.apache.commons.net.whois.WhoisClient;


public class WhoisSearch {
	
	private static Pattern pattern;
	private Matcher matcher;
	
	//whois parser regex
	private static final String WHOIS_SERVER_PATTERN = "Whois Server:\\s(.*)";
	
	static {
		pattern = Pattern.compile(WHOIS_SERVER_PATTERN);
	}
	
	private String getWhoisServer(String whois) {

		String rsb = "";

		matcher = pattern.matcher(whois);

		// get last whois server
		while (matcher.find()) {
			rsb = matcher.group(1);
		}
		return rsb;
	}
	
	private String queryWhois(String domain, String whoisServer) {

		String rsb = "";
		WhoisClient whois = new WhoisClient();
		try {

			whois.connect(whoisServer);
			rsb = whois.query(domain);
			whois.disconnect();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rsb;

	}
	
	public String getWhois(String domain) {
		StringBuilder sb = new StringBuilder("");

		WhoisClient whois = new WhoisClient();
		try {

		  whois.connect(WhoisClient.DEFAULT_HOST);

		  // whois =google.com
		  String whoisData1 = whois.query("=" + domain);

		  // append first result
		  sb.append(whoisData1);
		  whois.disconnect();

		  // get the google.com whois server - whois.markmonitor.com
		  String whoisServerUrl = getWhoisServer(whoisData1);
		  if (!whoisServerUrl.equals("")) {

			// whois -h whois.markmonitor.com google.com
			String whoisData2 = 
                            queryWhois(domain, whoisServerUrl);

			// append 2nd result
			sb.append(whoisData2);
		  }

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	public static void main(String[] args) {
		
<<<<<<< HEAD
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
	
=======
		
		
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
	
	
	
>>>>>>> c16d833c6c575cda901ba7b0c57998016b11d7ef
}
