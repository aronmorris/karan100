import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import com.jaunt.Document;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class PageScraper {

	private static UserAgent agent = new UserAgent();
	
	private static Document document;
	
	private static final URL scraperUrl; //this is the url that json requests are made of
	
	static { //static initialization
		URL temp;
		
		try {
			temp = new URL("http://www.tvguide.com/search/listings/");
		} catch (MalformedURLException e) {
			temp = null;
		}
		
		scraperUrl = temp;
		
	}
	
	private static final String AIRING_DATE_HTML = "<span class=\"airing-date-date\">";
	
	private static final String AIRING_TIME_HTML = "<span class=\"airing-date-time\">";
	
	private static final String AIRING_ELEMENT_HTML = "<div class=\"col-sm-2 airing-date\">";
	
	//adds the tracked show to the database
	private static void addTrackedShow(String showName, LocalDateTime datetime) {
		DatabaseManager.addEntry(showName, datetime);
	}
	
	private static LocalDateTime generateLocalDateTime(String date, String time) {
		LocalDateTime datetime;
		
		
		
		return datetime;
	}
	
	public static boolean searchShowtimes(String showName) throws ResponseException, NotFound {
		
		String term = showName.replaceAll(" ", "+"); 
		
		final String query = "?keyword=" + term; // search/?keyword=show+name in html query
		
		document = agent.visit(scraperUrl.toString() + query);
		
		Elements airdates = document.findEvery(AIRING_ELEMENT_HTML);
		
		if (!airdates.innerHTML().isEmpty()) {
			
			Elements daysOfAiring = airdates.findEvery(AIRING_DATE_HTML);
			
			Elements timesOfAiring = airdates.findEvery(AIRING_TIME_HTML);
			
			//TODO finish converting this day & time to a localdatetime for the database timestamp
			
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	public static void main(String[] args) throws NotFound, ResponseException {
		searchShowtimes("Modern Family");
	}
	
}
