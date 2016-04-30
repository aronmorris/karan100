import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	private String day, hour;
	
	public String getTime() {
		return this.day + ", " + this.hour;
	}
	
	public void setTime(String day, String hour, boolean repeats, String msg) {
		this.day = day;
		this.hour = hour;
		notifyAllObservers(this.day, this.hour, repeats, msg);
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers(String day, String hour, boolean repeats, String msg) {
		for (Observer obs : observers) {
			obs.update(day, hour, repeats, msg);
		}
	}
}
