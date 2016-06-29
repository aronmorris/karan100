import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class CountryFromIP {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the IP address you wish to look up.");
		
		String ip = sc.nextLine();
		
		sc.close();
		
		try {
			
			File database = new File("D:\\Programming\\Java\\JavaWorkspace\\Projects\\Country From IP\\GeoLiteCity.dat");
			
			LookupService cl = new LookupService(
					database, LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
			
			Location loc = cl.getLocation(ip);
			
			System.out.println("This IP address is located in: " + loc.countryName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e) {
			System.out.println("That address is invalid in the GeoLite database.");
		}
	
	}
	
}
