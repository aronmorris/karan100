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
		
		Date cDate = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE k:mm");
		
		try {
			cDate = sdf.parse(day + " " + hour);
			
			cal.setTime(cDate);
			System.out.println(sdf.format(cal.getTime()));
			System.out.println(cal.get(cal.HOUR_OF_DAY));
			System.out.println(cal.get(cal.MINUTE));
			System.out.println(cal.get(cal.DAY_OF_WEEK));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (repeats) {
			
			long interval = 
				Math.abs(cal.DAY_OF_WEEK * (1000 * 60 * 60 * 24 * 7) + cal.HOUR_OF_DAY * (1000 * 60 * 60) + cal.MINUTE * (1000 * 60));
			
			timer.scheduleAtFixedRate(new AlarmTask(msg), cDate, interval);
		}
		else {
			timer.schedule(new AlarmTask(msg), cDate);
		}
		
		return;
		
	}


}
