import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;


public class ZipCodeLocator {

	private String zipCode;
	
	private String latitude;
	private String longitude;
	
	public ZipCodeLocator(String zip) {
		zipCode = zip;
	}
	
	public String getGPSCoordinates() {
		//TODO todo placed for obvious tag where to place API 
		GeoApiContext context = new GeoApiContext().setApiKey("--API HERE--");
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, zipCode).await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Double.toString(results[0].geometry.location.lat) + ", " + Double.toString(results[0].geometry.location.lng);
	}
	
	public static void main(String[] args) {
		ZipCodeLocator zcl = new ZipCodeLocator("H9S 1M2");
		
		System.out.println(zcl.getGPSCoordinates());
		
	}
	
}
