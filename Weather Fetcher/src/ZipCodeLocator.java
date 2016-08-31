import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;


public class ZipCodeLocator {

	private String zipCode;
	
	private float latitude;
	private float longitude;
	
	public ZipCodeLocator(String zip) {
		zipCode = zip;
	}
	
	//latitude and longitude aren't assigned until strictly necessary
	void getGPSCoordinates() {
		//TODO todo placed for obvious tag where to place API 
		GeoApiContext context = new GeoApiContext().setApiKey("--GOOGLE API HERE--");
		GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context, zipCode).await(); //fetches gps coords from google using the zip
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assigns latitude and longitude from google's geometry data
		latitude = (float)results[0].geometry.location.lat;
		longitude =(float)results[0].geometry.location.lng; 
	}
	
	//lat and lng can't be null - float is a primitive and its default is 0.0f, not null
	public float getLat() {
		return latitude;
	}
	
	public float getLng() {
		return longitude;
	}
	
}
