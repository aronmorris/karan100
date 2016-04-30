public abstract class Observer {
	protected Subject subject;
	public abstract void update(String day, String hour, boolean repeats, String msg);
	
}
