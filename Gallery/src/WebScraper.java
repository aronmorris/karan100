import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {
	
	private String htmlContent;

	public WebScraper(URL url) {
		
		try (InputStream istream = url.openStream()) {
			
			//short to conserve memory space
			short character = 0;
			
			StringBuffer strBuffer = new StringBuffer();
			
			//append the html document a char at a time into the buffer
			//cast the int returned by the read to a short, then converts
			//the short to a char to append to the buffer
			while((character = (short) istream.read()) != -1) {
				strBuffer.append((char)character);
			}
			//finally puts the document into 
			htmlContent = strBuffer.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getLinks() {
		Document doc = Jsoup.parse(htmlContent);
		
		Elements links = doc.select("a");
		
		StringBuilder sb = new StringBuilder();
		
		for (Element e : links) {
			sb.append(e.attr("href") + "\n");
		}
		
		return sb.toString();
		
	}
	
	public String getImages() {
		//TODO add stuff to filter for pngs, jpegs, and gifs
		return null;
	}
	
	public static void main(String[] args) {
		
		URL url = null;
		try {
			url = new URL("https://jsoup.org/cookbook/extracting-data/working-with-urls");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebScraper ws = new WebScraper(url);
		
		System.out.println(ws.getLinks());
		
	}
	
}
