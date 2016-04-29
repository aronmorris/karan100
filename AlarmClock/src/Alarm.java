import java.util.Timer;


public class Alarm extends Observer{

	private Timer timer = new Timer();
	
	public Alarm(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update(int time, String msg) {
		
		timer.schedule(new AlarmTask(msg), time);
	}

}
