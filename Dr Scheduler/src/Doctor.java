import java.time.Duration;


public class Doctor {

	private LimitedLinkedList<Patient> patients;
	
	private final int MAX_PATIENTS;
	
	private Duration timeLeftInDay = Duration.ofHours(8); //8 hours in a workday
	
	private Duration currentTimeOccupied;
	
	public Doctor(int maxPatientsPerDay) {
		MAX_PATIENTS = maxPatientsPerDay;
		
		patients = new LimitedLinkedList<Patient>(MAX_PATIENTS);
		
		currentTimeOccupied = Duration.ZERO;
		
	}
	
	public boolean cure() {
		Patient p = patients.pop();

		return p.cure();
		
	}
	
	public boolean hasPatients() {
		return !patients.isEmpty();
	}
	
	public boolean add(Patient p) {
		//If the time it'll take to cure this patient plus the time already occupied is less
		//than the time left in the day to work, add the patient to the list
		//Otherwise, no-go
		if (currentTimeOccupied.plus(p.timeToHealth()).toMillis() <= timeLeftInDay.toMillis()) {
			patients.add(p);
			currentTimeOccupied = currentTimeOccupied.plus(p.timeToHealth());
			timeLeftInDay = timeLeftInDay.minus(p.timeToHealth());
			return true;
		}
		
		else {
			System.out.println("This doctor fully booked.");
			return false;
		}
		
		
	}

	
}
