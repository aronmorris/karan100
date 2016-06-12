import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;


public class Scheduler {

	public static void main(String[] args) throws InterruptedException {
		
		Timer time = new Timer();
		
		ScheduledSearch ss = new ScheduledSearch();
		
		Date firstTime = new Date();
		long period = 1;
		ArrayList<String> arr = new ArrayList<String>();	
		
		for (String arg : args) {
			try {
				if (Integer.parseInt(arg) != 0) {
					period = Integer.parseInt(arg);
				}
			} catch(NumberFormatException e) {
				arr.add(arg);
			}
		}
		
		String[] strArr = new String[arr.size()];
		
		ss.setSymbolsToSearch(arr.toArray(strArr));
		
		time.schedule(ss, firstTime, period);
		
	}
	
}
