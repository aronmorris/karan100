import java.util.ArrayList;
import java.util.Iterator;


public class GuestBook {

	private ArrayList<Post> posts;
	
	public GuestBook() {
		this.posts = new ArrayList<Post>();
	}
	
	public void writePost(String comment) {
		posts.add(new Post(comment));
	}
	
	public void removePost(Post post) {
		posts.remove(post);
	}
	
	public void removePost(String post) {
		for (Iterator<Post> it = posts.iterator(); it.hasNext();) {
			Post p = it.next();
			if (p.getContent().equals(post)) {
				it.remove();
				break;
			}
		}
	}
	
	public ArrayList<Post> getPosts() {
		return new ArrayList<Post>(posts);
	}
	
	
	
}
