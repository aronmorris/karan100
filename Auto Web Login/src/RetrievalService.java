import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class RetrievalService implements Runnable {

	SiteLogin loggedSite;
	
	boolean running = false; //returns true if the program is currently running a retrieval
	
	public RetrievalService(String username, String password) {
		loggedSite = new SiteLogin("https://www.facebook.com/", username, password);
	}
	
	//registers the run method with the ExecutorService with the according delay between runs
	//period is in seconds
	public void initiate(long period) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    
		final ScheduledFuture<?> retriever = scheduler.scheduleAtFixedRate(this, 1, period, TimeUnit.SECONDS);
	   
		running = true;
		
		scheduler.schedule(new Runnable() {
			public void run() { 
				retriever.cancel(true); 
				running = false;	}
			}, 60, TimeUnit.SECONDS); //timeout after 1 minute
	}
	
	//appends the contents of the logged-in page to the relevant file
	@Override
	public void run() { 
		List<String> pageContent = new LinkedList<String>();
		
		for (String s : loggedSite.login().asText().split("\\r?\\n")) { //covers the newline characters in Unix & Win OSes
			pageContent.add(s);
		}
		
		pageContent.add("\n\n"); //append double newline to break old content from new
		
		Path file = Paths.get("C:/Users/Aron/Desktop/AutoLogIn/savedContent.txt");
		
		try {
			Files.write(file, pageContent, Charset.forName("UTF-8"), StandardOpenOption.APPEND); //appends content to the file
			
			System.out.println("Content saved!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}