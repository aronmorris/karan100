public abstract class Observer {
	protected Subject subject;
	public abstract void update(int time, String msg);
	
}
