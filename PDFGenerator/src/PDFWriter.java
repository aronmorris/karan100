import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


public class PDFWriter {

	private final static int FONT_SIZE = 12;
	
	public PDFWriter() {
		
		
	}
	
	public static boolean convertHtmlToPDF(File htmlToConvert) {
		
		boolean error = false;
		
		System.out.printf("Converting HTML: %s to PDF at: %s%n", htmlToConvert.getName(), htmlToConvert.getAbsolutePath());
		
		String fileLoc = htmlToConvert.getAbsolutePath();
		
		try (OutputStream pdfOut = new FileOutputStream(new File(htmlToConvert.getAbsolutePath().replace(".html", ".pdf")))){
			
			Document doc = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(doc, pdfOut);
			
			doc.open();
			
			 XMLWorkerHelper.getInstance().parseXHtml(writer, doc, new FileInputStream(fileLoc));
			
			 doc.close();
			 
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			error = true;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = true;
		}
		
		return !error;
		
	}
	
	public static boolean convertTextToPDF(File fileToConvert) throws IOException {
		
		BufferedReader br = null;
		
		boolean error = false;
		
		System.out.printf("Converting text: %s to PDF at: %s%n", fileToConvert.getName(), fileToConvert.getAbsolutePath());
		
		try { 
			
			if (fileToConvert.exists()) {
			
				Document pdfDoc = new Document(PageSize.A4);
				
				String currentLine;
			
				Font font = preprocessAndReturnFont(pdfDoc, fileToConvert);
				
				br = new BufferedReader(new FileReader(fileToConvert));
				
				while ((currentLine = br.readLine()) != null) {
					Paragraph paragraph = new Paragraph(currentLine + "\n", font);
					paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
					pdfDoc.add(paragraph);
				}
				
				pdfDoc.close();
				
				System.out.println("Conversion complete.");
				
			} else {
				System.err.println("File not found.");
				error = true;
			}
		} catch(DocumentException e) {
			System.err.println("Something went wrong with the Document.");
			e.printStackTrace();
			error = true;
		} catch (IOException e) {
			System.err.println("IO exception occurred!");
			e.printStackTrace();
			error = true;
		} finally {
			if (br != null) {
				br.close();
			}
		}
		
		return !error;
		
	}
	//Does some preprocessing work on the document to pretty it up
	private static Font preprocessAndReturnFont(Document doc, File fileToConvert) throws DocumentException, IOException {
		
		String outputFile = fileToConvert.getAbsolutePath().replace(".txt", ".pdf");
		
		PdfWriter.getInstance(doc, new FileOutputStream(outputFile)).setPdfVersion(PdfWriter.VERSION_1_7);
		
		doc.open();
		
		Font font = new Font(BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED));
		
		font.setStyle(Font.NORMAL);
		
		font.setSize(FONT_SIZE);
		
		doc.add(new Paragraph("\n"));
		
		return font;
	}
	
}
