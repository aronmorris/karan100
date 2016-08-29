import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class AtomicClock {

	//the URL which displays atomic time and UTC on its page in plain HTML
	private final String ATOMIC_URL = "http://www.timeanddate.com/time/international-atomic-time.html";
	//executor to continuously fetch the updated time from timeanddate
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	//atomicclock is a container for the Jsoup methods that fetch the data
	public AtomicClock() {
	
	}
	//connects once to timeanddate, returns the relevant data
	public String getAtomicTime() {
		
		Document doc = null;
		
		try {
			doc = Jsoup.connect(ATOMIC_URL).get(); //establish an HTML doc from the contents of timeanddate
		} catch (IOException e) {
			e.printStackTrace();
			return ""; //return nothing if the url failed to connect for some reason
		}
		
		Element utcTime = doc.select("span#hourmin1").first(); //gets the hour:minute portion of cUTC on timeanddate.com
		Element utcTimeSeconds = doc.select("span#sec1").first(); //gets the second portion, which is in a different span ID
		
		return utcTime.text() + ":" + utcTimeSeconds.text(); //returns UTC as hour:minute:seconds
		
	}
	
	//calls getAtomicTime() once per second, keeping the time updated
	public void getAtomicTimeContinuously() {
		final Runnable clock = new Runnable() {
			public void run() {
				System.out.println(getAtomicTime());
			}
		};
		final ScheduledFuture<?> clockHandle = scheduler.scheduleAtFixedRate(clock, 1, 1, TimeUnit.SECONDS); //schedules the clock runnable to run once per second
		
		scheduler.schedule(new Runnable() { //cancels the clock's operation after 1 hour of continuous activity
			public void run(){ clockHandle.cancel(true); }
		}, 60 * 60, TimeUnit.SECONDS);
		
	}
	
	public static void main(String[] args) {
		
		AtomicClock clock = new AtomicClock();
		
		System.out.println("Clock reports coordinated UTC time as: ");
		clock.getAtomicTimeContinuously();
		
	}
	
}
