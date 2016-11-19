
public class TrackerMain {

	public static void main(String[] args) {
		
	}
	
	public static void update() {
		//TODO this method will handle updating the database, making new requests of the scraper, and sending emails
		//through making requests to the relevant classes
	}
	
	private static void sendEmail(String contents) {
		
		EmailSender.sendMail(contents);
		
	}
	
	private static void scrapePage() {
		//TODO handle pagescraping logic
	}
	
	private static void updateDatabase() {
		//TODO update DB with add/remove operations
	}
	
}
