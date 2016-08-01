import java.util.LinkedList;

//LinkedList wrapper class which has a maximum size and removes elements from the list head to keep it that way
public class LimitedLinkedList<E> extends LinkedList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int MAX_SIZE;
	
	public LimitedLinkedList(int maxSize) {
		MAX_SIZE = maxSize;
	}
	
	@Override
	public boolean add(E o) {
		super.add(o);
		while (size() > MAX_SIZE) {
			super.remove();
		}
		return true;
	}
	
	
}
