import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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
	
	//writes the result of the indexing to a new file in an index folder
	//in the same folder as the program is in.
	public static boolean writeResultsToFile(String fileName, String contents) {
		
		String user = System.getenv("USERNAME");
		
		File file = new File("C:\\Users\\" + user + "\\Desktop\\Indexes\\" + fileName + ".txt");
		
		file.getParentFile().mkdirs();
	
		try {
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); //creates writer to write to the file
			
			for (String line : contents.split("\n")) {
				writer.write(line);
				writer.newLine();
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
				
		URL url = null;
		
		Scanner sc = new Scanner(System.in);
		do {
			
			System.out.println("Enter site URL.");
			
			String siteURL = sc.next();
			
			try {
				url = new URL(siteURL);
				break;
			} catch (MalformedURLException e) {
				System.out.println("Not a valid URL!");
			}
		
		} while(true);
		
		WebScraper ws = new WebScraper(url);
		
		System.out.println("Enter desired filename.");
		
		String indexFileName = sc.next();
		
		sc.close();
		
		writeResultsToFile(indexFileName, ws.getLinks() + "\n" + ws.getImages());
		
	}
	
}
