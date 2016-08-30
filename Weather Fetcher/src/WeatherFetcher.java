import java.io.IOException;
import java.util.Scanner;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import org.json.JSONException;


public class WeatherFetcher {

	private static OwmClient owm = new OwmClient(); //no need for multiple owm clients for however many weatherfetcher objects
	
	private float gpsLat, gpsLng;
	
	private final int WEATHER_STATION_COUNT = 5;
	
	public WeatherFetcher(ZipCodeLocator loc) {
		loc.getGPSCoordinates(); //gps coordinates is created and assigned
		gpsLat = loc.getLat();
		gpsLng = loc.getLng();
	}
	
	public String getWeather() {
		try {
			WeatherStatusResponse currentWeather = owm.currentWeatherAtCityCircle(gpsLat, gpsLng, WEATHER_STATION_COUNT);
			
			if (currentWeather.hasWeatherStatus()) {
				WeatherData weather = currentWeather.getWeatherStatus().get(0);
				if (weather.getPrecipitation() == Integer.MIN_VALUE) {
					return weather.getWeatherConditions().get(0).getDescription();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		WeatherFetcher wf = new WeatherFetcher(new ZipCodeLocator("H9S 1M2"));
		
		System.out.println(wf.getWeather());
		
		
		
	}
	
}
