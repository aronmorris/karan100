import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.json.JSONObject;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class PageScraper {

	private static UserAgent agent = new UserAgent();
	
	private static Document document;
	
	private static final URL scraperUrl; //this is the url that json requests are made of
	
	static {
		URL temp;
		
		try {
			temp = new URL("http://www.tvguide.com/search/listings/");
		} catch (MalformedURLException e) {
			temp = null;
		}
		
		scraperUrl = temp;
		
	}
	
	
	//adds the tracked show to the database
	public static void addTrackedShow(String showName, String postalCode) {
		
	}
	
	
	public static boolean searchShowtimes(String showName) throws ResponseException, NotFound {
		
		String term = showName.replaceAll(" ", "+"); 
		
		final String query = "?keyword=" + term; // search/?keyword=show+name in html query
		
		document = agent.visit(scraperUrl.toString() + query);
		
		System.out.println(document.findFirst("<div class=\"airing section-xs\">").innerHTML()); //debug
		
		return true;
		
	}
	
	public static void main(String[] args) throws NotFound, ResponseException {
		searchShowtimes("Modern Family");
	}
	
}
