
public class schedulerMain {

	public static void main(String[] args) {
		
		Doctor phil = new Doctor(8);
		
		phil.add(new Patient(30));
		
		phil.add(new Patient(0));
		
		phil.add(new Patient(1));
		
		while(phil.hasPatients()) {
			phil.cure();
		}
		
	}
	
}
