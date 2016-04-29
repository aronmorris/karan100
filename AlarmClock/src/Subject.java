import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	private int time;
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int Time, String msg) {
		this.time = Time;
		notifyAllObservers(this.time, msg);
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers(int time, String msg) {
		for (Observer obs : observers) {
			obs.update(time, msg);
		}
	}
}
