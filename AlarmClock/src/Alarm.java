import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class Alarm extends Observer{

	private Timer timer = new Timer();
	private Calendar cal = Calendar.getInstance();
	
	public Alarm(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update(String day, String hour, boolean repeats, String msg) {
		
		cal.set(Calendar.DAY_OF_WEEK, parseDay(day));
		parseHour(hour);
		
		timer.schedule(new AlarmTask(msg), cal.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}

	/**
	 * Parses a string of form "[int] [AM/PM]" and returns the time from it
	 * @param hour
	 * @return
	 */
	protected int parseHour(String hour) {
		int offset = 0;
		int rHour = 0;
		
		if (hour.contains(hour.toUpperCase())) {
			offset = 12;
		}
		
		rHour = Integer.parseInt(hour.substring(0, hour.indexOf(':')));
		System.out.println("Hours: " + (rHour + offset));
		return rHour + offset;
		
	}
	
	//TODO reformat this so the minute parameter returns validly
	protected void parseMinute(String hour) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date = null;
		try {
			date = sdf.parse(hour);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		
	}
	
	protected int parseDay(String day) {
		int rDay= 0;
		final String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		
		for (int i = 0; i < Calendar.SATURDAY; i++) {
			if (daysOfWeek[i].equalsIgnoreCase(day)) {
				rDay = i;
			}
		}
		System.out.println("Days: " + rDay);
		return rDay;
	}

}
