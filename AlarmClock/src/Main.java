
public class Main {
	public static void main(String[] args) {
		Subject subj1 = new Subject();
		
		Alarm al1 = new Alarm(subj1);
		
		//set time in millis for ringing
		//subj1.setTime(10000, "Hello");
		
		subj1.setTime(3000, 
				"/Users/Aron/comp345project/COMP345Project/COMP345Project/SFML-2.1/examples/sound/resources/canary.wav");
	
	}
}
