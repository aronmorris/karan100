import java.util.ArrayList;
import java.util.Date;


public class Post {

	private Date timestamp;
	private ArrayList<Post> comments;
	private String content;
	
	public Post(String content) {
		this.content = content;
		comments = new ArrayList<Post>();
		timestamp = new Date();
	}
	
	public void addComment(String comment) {
		comments.add(new Post(comment));
	}
	
	public void removeComment(Post comment) {
		comments.remove(comment);
	}
	
	/**
	 * Returns the first comment matching this one in the comments belonging to this post
	 * Returns null if no comment matches
	 * @param comment Comment to be searched for
	 * @return
	 */
	public Post getComment(Post comment) {
		for (Post c : comments) {
			if (c.equals(comment)) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Post)) {
			return false;
		}
		
		Post p = (Post) o; 
		
		if (!p.timestamp.equals(this.timestamp)) {
			return false;
		}
		
		if (!p.comments.equals(this.comments)) {
			return false;
		}
		
		if (!p.content.equals(this.content)) {
			return false;
		}
		
		return true;
	}
}
