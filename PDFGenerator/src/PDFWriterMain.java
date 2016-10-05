import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class PDFWriterMain {
	
	final static String EXIT = "exit";

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\\n");
		
		String fileLoc;
		
		do {
			fileLoc = sc.nextLine();
			
			if (fileLoc.contains(".txt")) {
				PDFWriter.convertTextToPDF(new File(fileLoc));
			}
			else if (fileLoc.contains(".html")) {
				PDFWriter.convertHtmlToPDF(new File(fileLoc));
			}
			else {
				 if (!fileLoc.toLowerCase().equals(EXIT)) {
					 System.out.println("File path entered invalid.");
					 System.out.printf("Type %s to exit.%n", EXIT);
				 }
			}
			
		} while(!fileLoc.toLowerCase().equals(EXIT));
		
		sc.close();
	}
	
}
