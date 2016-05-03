import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

/**
 * Uses Wolfram Alpha's API to fetch city distances
 * 
 * This might technically be a math problem but the list also says it may need to 
 * find coordinates like latitude and longitude and Wolfram does it for me
 * @author Aron
 *
 */
public class CoordinateCalculator {

	private static String appid = "P2KE2J-LAEGPRP294";
	
	private String fCity, sCity;
	
	private WAEngine engine = new WAEngine();
	private WAQuery query = engine.createQuery();
	
	public CoordinateCalculator() {
		engine.setAppID(appid);
		engine.addFormat("plaintext");
	}
	
	public CoordinateCalculator(String firstCity, String secondCity) {
		this.setFirstCity(firstCity);
		this.setSecondCity(secondCity);
		engine.setAppID(appid);
		engine.addFormat("plaintext");
	}
	
	public void query() {
		
		String result = "";
		
		query.setInput("Distance between " + this.getFirstCity() + " and " + this.getSecondCity());
		try {
			//System.out.println("Query URL: ");
			//System.out.println(engine.toURL(query));
			System.out.println("Querying! Please wait a moment.");
			
			//Sends the query URl to WA, gets the XML, and parses it into an object
			WAQueryResult qResult = engine.performQuery(query);
			
			if (qResult.isError()) {
				System.out.println("Error in query.");
				System.out.println("Error: " + qResult.getErrorCode());
				System.out.println(qResult.getErrorMessage());
			}
			else if (!qResult.isSuccess()) {
				System.out.println("Query failed, no result.");
			}
			else {
				for (WAPod p : qResult.getPods()) {
					if (!p.isError()) {
						if (p.getTitle().equals("Result")) {
							for (WASubpod sp : p.getSubpods()) {
								for (Object element : sp.getContents()) {
	                                if (element instanceof WAPlainText) {
	                                   result = ((WAPlainText) element).getText();
	                                }
	                            }

							}
						}
					}
				}
			}
			
		} catch (WAException e) {
			e.printStackTrace();
		}
		
		if (!result.equals("")) {
			System.out.printf("Distance between %s and %s is "+ result, this.fCity, this.sCity);
		}
	}

	public String getFirstCity() {
		return fCity;
	}

	public void setFirstCity(String fCity) {
		this.fCity = fCity;
	}

	public String getSecondCity() {
		return sCity;
	}

	public void setSecondCity(String sCity) {
		this.sCity = sCity;
	}
	
}
