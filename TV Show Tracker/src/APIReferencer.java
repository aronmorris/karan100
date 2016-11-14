import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class APIReferencer {
	
	enum LineupType {
		SAT("SAT"),
		CAB("CAB");
		
		private String type;
		
		public String toString() {
			return type;
		}
		
		LineupType(String type) {
			this.type = type;
		}
	}

	private Document doc;
	
	private UserAgent agent;
	
	private String apiKey;
	
	private URL apiUrl; //this is the url that json requests are made of
	
	public APIReferencer(URL url) {
		
		agent = new UserAgent();
		
		try {
			agent.visit(url.toString());
			
			doc = agent.doc;
			
			apiUrl = new URL("http://api.tvmedia.ca/tv/v4");
			
			apiKey = fetchAPIKey();
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//adds the tracked show to the database
	public void addTrackedShow(String showName, String postalCode) {
		
	}
	
	//searches the returned json for what it is one seeks
	private Document searchGuide(String searchTerm) throws NotFound, ResponseException {
		
		Form searchForm = doc.getForm("<form id=listings-search>");
		
		searchForm.submit();
		
		
		
	}
	//establishes the lineup according to api instructions
	//API lineupIDs for cable and satellite are then made in another request
	private List<String> establishLineup(String postalCode) {
		
		String lineupURL = getLineupURL(postalCode);
		
	}
	
	private String getLineupURL(String postal) {
		return "http://api.tvmedia.ca/tv/v4/lineups?api_key=" + apiKey + "&postalCode=10001";
	}
	
	private String fetchAPIKey() throws IOException {
		File apikey = new File("apikey.txt");
		
		String api = null;
		
		try (BufferedReader bf = new BufferedReader(new FileReader(apikey))) {
			
			String line = null;
			
			while ( (line = bf.readLine()) != null) {
				api = line;
			}
		} finally {
			return api;
		}
	
	}
	
}
