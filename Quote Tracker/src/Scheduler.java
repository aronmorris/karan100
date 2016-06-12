import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;


public class Scheduler {

	public static void main(String[] args) throws InterruptedException {
		
		Timer time = new Timer();
		
		final int SEC = 1000, MIN = SEC * 60, HOUR = MIN * 60, DAY = HOUR * 24, WEEK = DAY * 7;
		
		ScheduledSearch ss = new ScheduledSearch();
		
		Date firstTime = new Date();
		long period = 1;
		ArrayList<String> arr = new ArrayList<String>();	
		
		for (String arg : args) {
			if (arg.matches("[0-9][mhdw]")) {
				int interval = Integer.parseInt(arg.substring(0, 1));
				switch(arg.substring(1)) {
				case "m": interval *= MIN;
					break;
				case "h": interval *= HOUR;
					break;
				case "d": interval *= DAY;
					break;
				case "w": interval *= WEEK;
					break;
				default: 
					break;
				}
				period = interval;
			}
			else {
				arr.add(arg);
			}
		}
		
		String[] strArr = new String[arr.size()];
		
		ss.setSymbolsToSearch(arr.toArray(strArr));
		
		time.schedule(ss, firstTime, period);
		
	}
	
}
