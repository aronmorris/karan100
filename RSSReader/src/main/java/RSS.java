import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;


public class RSS {
	
	private SyndFeed feed;
	
	public static void main(String[] args) {
		
		URL url;
		try {
			url = new URL("http://xkcd.com/rss.xml");
			RSS rss = new RSS(url);
			
			System.out.println(rss.read());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public RSS(URL rssURL) {
		SyndFeedInput input = new SyndFeedInput();
		try {
			feed = input.build(new XmlReader(rssURL));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String read() {
		return feed.toString();
	}
	
}
