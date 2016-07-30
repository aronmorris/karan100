import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Patient implements Runnable {
	
	private boolean isCured;
	
	private Duration timeToHealthy;
	
	public Patient(long timeToHealthInMinutes) {
		isCured = false;
		timeToHealthy = Duration.ofMinutes(timeToHealthInMinutes);

	}
	
	
	
	public boolean cure() {
		//TODO redo this bsns
		
		System.out.println("Doctor has started to cure this patient.");
		
		final ScheduledExecutorService cureTime = Executors.newScheduledThreadPool(1);
		
		cureTime.schedule(this, timeToHealthy.toMinutes(), TimeUnit.MINUTES);
		
		System.out.println("The patient is cured!");
		
		return isCured;
	}

	public boolean isCured() {
		return isCured;
	}



	@Override
	public void run() {
		System.out.println("Doctor is curing the patient.");
		
	}
}
