import java.util.TimerTask;


public class AlarmTask extends TimerTask {

	private String msg;
	
	public AlarmTask(String msg) {
		this.msg = msg;
	}
	public void run() {
		System.out.println(msg);
	}
	
}
