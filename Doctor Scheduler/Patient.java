import java.time.Duration;


public class Patient {
	
	private boolean isCured;
	
	private Duration timeToHealthy;
	
	private Timer timer;
	
	public Patient(long timeToHealthInSeconds) {
		isCured = false;
		timeToHealthy = new Duration(timeToHealthInMillis);
		timer = new Timer();
	}
	
	public boolean cure() {
		if (System.currentTimeMillis() >= (timeToHealthy.toMillis())){
			isCured = true;
		}
		return isCured;
	}

	public boolean isCured() {
		return isCured;
	}
}
