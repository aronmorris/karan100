import java.time.Duration;


public class Doctor {

	private LimitedLinkedList<Patient> patients;
	
	private final int MAX_PATIENTS;
	
	private final Duration WORKDAY_LENGTH = Duration.ofHours(8);
	
	public Doctor(int maxPatientsPerDay) {
		MAX_PATIENTS = maxPatientsPerDay;
		
		patients = new LimitedLinkedList<Patient>(MAX_PATIENTS);
		
	}
	
	public boolean cure() {
		Patient p = patients.pop();
		
		//work on the patient, crude implementation but finer not strictly needed
		//since a doctor can only do one patient at a time
		//Thought: Consider multiple doctors on one thread - make the object itself synced to a thread?
		while(!p.isCured()) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return true;
		
	}
	
	public boolean add(Patient p) {
	}

	
}
