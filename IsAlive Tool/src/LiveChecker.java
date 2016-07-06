import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class LiveChecker {

	static class CheckRunnable {
		
		private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		private String url;
		
		private int timeout;
		
		public CheckRunnable(String url, int timeout) {
			this.url = url;
			this.timeout = timeout;
		}
		
		public void checkEveryMinute() {
			
			final Runnable checker = new Runnable() {
				public void run() { testConnection(url, timeout); }
			};
			//schedules check on url every minute
			final ScheduledFuture<?> checkHandler =
					scheduler.scheduleAtFixedRate(checker, 1, 1, TimeUnit.MINUTES);
			//stop checking after a day by default
			scheduler.schedule(new Runnable() {
				public void run() {checkHandler.cancel(true); }
			}, 60 * 60, TimeUnit.MINUTES); 
		
			
		}
	}
	
	static final int ECHO_PORT = 7;
	
	public static void main(String[] args) {
		
		//protocol amended to url if missing
		String url = args[0].contains("http://") ? args[0] : "http://" + args[0];
		
		int timeout = Integer.parseInt(args[1]);
		
		CheckRunnable checker = new CheckRunnable(url, timeout);
		
		checker.checkEveryMinute();
		
		
	}
	
	public static boolean testConnection(String url, int timeout) {
		
		try {
			
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			
			connection.setConnectTimeout(timeout);
			
			connection.setReadTimeout(timeout);
			
			connection.setRequestMethod("HEAD");
			
			int response = connection.getResponseCode(); //200-399 ok
			
			if (200 <= response && response <= 399) {
				
				System.out.println(response + " ok");
				
				return true;
			}
			
		} catch (IOException e) {
			
			System.out.println("HTTP code not in range 200-399 KO");	
			
			System.out.println("URL not responding!");
			
			return false;
			
			
			
		}
		
		return false;
		
	}
	
}
