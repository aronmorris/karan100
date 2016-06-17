import java.util.Date;


public class Post {
	
	private String content;
	private Date timestamp;

	public Post(String content) {
		this.content = content;
		timestamp = new Date();
	}
	
	@Override
	public String toString() {
		return "\"" + content + "\"" + " -Posted at: " + timestamp.toString().substring(0, 19);
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
		
		if (!p.content.equals(this.content)) {
			return false;
		}
		
		return true;
	}
}
