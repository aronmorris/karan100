
public class Doctor {

	private LimitedLinkedList<Patient> patients;
	
	private final int MAX_PATIENTS;
	
	public Doctor(int maxPatientsPerDay) {
		MAX_PATIENTS = maxPatientsPerDay;
		
		patients = new LimitedLinkedList<Patient>(MAX_PATIENTS);
		
	}
	
	
	
}
