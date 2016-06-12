import java.io.IOException;
import java.util.TimerTask;

import org.json.JSONException;


public class ScheduledSearch extends TimerTask {

	private String[] symbols;
	
	@Override
	public void run() {
		try {
			QuoteTracker.getData(QuoteTracker.readJsonFromUrl(QuoteTracker.generateURL(symbols)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setSymbolsToSearch(String[] symbols) {
		this.symbols = symbols;
	}

}
