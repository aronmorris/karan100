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
		
		SimpleDateFormat sdf = new SimpleDateFormat("k");
		
		try {
			cDate = sdf.parse(hour);
			
			cal.setTime(cDate);
			System.out.println(cal.HOUR_OF_DAY);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (repeats) {
			timer.scheduleAtFixedRate(new AlarmTask(msg), cDate, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
		}
		else {
			
		}
		
		timer.schedule(new AlarmTask(msg), cal.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}


}
