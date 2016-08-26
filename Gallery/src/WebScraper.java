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
		
		int ctr = 0;
		
		for (Element e : links) {
			sb.append(e.absUrl("href").toString() != "" ? e.absUrl("href").toString() + "\n" : "");
			
			if (!e.absUrl("href").toString().isEmpty()) {
				ctr++;
			}
			
			//System.out.println(e.toString());
		}
		
		System.out.println("URLs found: " + ctr + "\n");
		
		return sb.toString();
		
	}
	
	public String getImages() {
		Document doc = Jsoup.parse(htmlContent);
		
		StringBuilder sb = new StringBuilder();
		
		Elements images = doc.select("img");
		
		int ctr = 0;
		
		for (Element e: images) {
			String absImg = e.attr("abs:src");
			if (!absImg.isEmpty()) {
				sb.append(absImg + "\n");
				ctr++;
			}
			
		}
		
		System.out.println("Image URLs found: " + ctr + "\n");
		
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		
		//TODO write results to file
		
		URL url = null;
		try {
			url = new URL("http://drmcninja.com/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebScraper ws = new WebScraper(url);
		
		System.out.println(ws.getLinks());
		
		System.out.println(ws.getImages());
		
	}
	
}
