
public class schedulerMain {

	public static void main(String[] args) {
		
		Doctor phil = new Doctor(8);
		
		phil.add(new Patient(120));
		
		phil.add(new Patient(120));
		
		phil.add(new Patient(120));
		
		phil.add(new Patient(120));
		
		phil.add(new Patient(5000));
		
		//should only take 8 patients
		for (int i = 0; i <= 10; i++) {
			phil.add(new Patient(i));
		}
		
		while(phil.hasPatients()) {
			phil.cure();
		}
		
	}
	
}
