import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


public class Alarm extends Observer {

	private Timer timer = new Timer();
	private Calendar cal = Calendar.getInstance();
	
	public Alarm(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update(String day, String hour, boolean repeats, String msg) {
		
		Date cDate = new Date();
		
		//System.out.println("Alarm update method fired");
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE k:mm");
		
		try {
			cDate = sdf.parse(day + " " + hour);
			
			cal.setTime(cDate);
			//System.out.println(sdf.format(cal.getTime()));
			//System.out.println(cal.get(cal.HOUR_OF_DAY));
			//System.out.println(cal.get(cal.MINUTE));
			//System.out.println(cal.get(cal.DAY_OF_WEEK));
			
			//System.out.println("Calendar time: " + cal.getTime());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (repeats) {
			
			long interval = 
				Math.abs(Calendar.DAY_OF_WEEK * (1000 * 60 * 60 * 24 * 7) + Calendar.HOUR_OF_DAY * (1000 * 60 * 60) + Calendar.MINUTE * (1000 * 60));
			
			//System.out.println(interval);
			
			//interval = 1000 * 10;
			
			timer.schedule(new AlarmTask(msg), cDate, interval);
		}
		else {
			timer.schedule(new AlarmTask(msg), cDate);
		}
		
		return;
		
	}


}
