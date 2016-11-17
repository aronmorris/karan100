import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
	
	private static LocalDateTime createLocalDateTimeFromString(String airdate, String airtime) {
		//formatters designed to capture the strings scraped from the tv guide listing
		final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd"); //e.g. NOV 18
		final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma"); //e.g. 11:30PM
		
		LocalDate dateComponent = LocalDate.parse(airdate, dateFormatter);
		
		LocalTime timeComponent = LocalTime.parse(airtime, timeFormatter);
		
		LocalDateTime dateTime = LocalDateTime.of(dateComponent, timeComponent);
		
		return dateTime;
		
	}
	
	public static void main(String[] args) throws NotFound, ResponseException {
		searchShowtimes("Modern Family");
		
		System.out.println(DatabaseManager.getDateTimeStamp(createLocalDateTimeFromString("NOV 20", "11:30PM")));
		
	}
	
}
