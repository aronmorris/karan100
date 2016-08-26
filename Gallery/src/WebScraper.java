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
		
		//Adds a url to the output only if it's an "a" tag and has a "href"
		//the url added is the absolute url, not the page-relative one
		
		for (Element e : links) {
			sb.append(e.absUrl("href").toString() != "" ? e.absUrl("href").toString() + "\n" : "");
			//System.out.println(e.toString());
		}
		
		return sb.toString();
		
	}
	
	public String getImages() {
		Document doc = Jsoup.parse(htmlContent);
		
		Elements images = doc.select("img");
		System.out.println(images.size());
		for (int i = 0; i < images.size(); i++) {
			Element e = images.get(i);
			System.out.println(e.absUrl("src").toString() != "" ? e.absUrl("src").toString() + "\n" : "");
			
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		URL url = null;
		try {
			url = new URL("http://xkcd.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebScraper ws = new WebScraper(url);
		
		System.out.println(ws.getLinks());
		
		System.out.println(ws.getImages());
		
	}
	
}
