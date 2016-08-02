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
	
	public Duration timeToHealth() {
		return timeToHealthy;
	}
	
	public boolean cure() {
		
		System.out.println("Doctor has started to cure this patient.");
		
		final ScheduledExecutorService cureTime = Executors.newScheduledThreadPool(1);
		
		cureTime.schedule(this, timeToHealthy.toMinutes(), TimeUnit.MINUTES);
		
		Duration elapsed = Duration.ZERO;
		
		final long TEN_SECONDS = 10;
		
		final long TEN_IN_MILLIS = 10000;
		
		while(!isCured) {
			try {
				Thread.sleep(TEN_IN_MILLIS);
				
				elapsed = elapsed.plus(Duration.ofSeconds(TEN_SECONDS));
				
				System.out.printf("%d seconds have elapsed since the doctor began work on this patient.%n", elapsed.toMillis() / 1000);
				
				System.out.printf("There are %.2f minutes left.%n", (float)(timeToHealthy.minus(elapsed).toMillis() / 1000) / 60);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Patient cured!");
		return isCured;
	}

	public boolean isCured() {
		return isCured;
	}



	@Override
	public void run() {
		System.out.println("Doctor is curing the patient.");
		
		isCured = true;
		
	}
}
