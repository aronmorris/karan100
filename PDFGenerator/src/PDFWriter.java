import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFWriter {

	private File fileToConvert;
	
	private final int FONT_SIZE = 12;
	
	public PDFWriter(File f) {
		
		fileToConvert = f;
		
	}
	
	public boolean convertTextToPDF() {
		
		BufferedReader br = null;
		
		boolean error = false;
		
		try { 
			
			if (fileToConvert.exists()) {
			
				Document pdfDoc = new Document(PageSize.A4);
				
				String currentLine;
			
				Font font = preprocessAndReturnFont(pdfDoc);
				
				br = new BufferedReader(new FileReader(fileToConvert));
				
				while ((currentLine = br.readLine()) != null) {
					Paragraph paragraph = new Paragraph(currentLine + "\n", font);
					paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
					pdfDoc.add(paragraph);
				}
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
		}
		
		return !error;
		
	}
	//Does some preprocessing work on the document to pretty it up
	private Font preprocessAndReturnFont(Document doc) throws DocumentException, IOException {
		
		String outputFile = fileToConvert.getName().replace(".txt", ".pdf");
		
		PdfWriter.getInstance(doc, new FileOutputStream(outputFile)).setPdfVersion(PdfWriter.VERSION_1_7);
		
		doc.open();
		
		Font font = new Font(BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED));
		
		font.setStyle(Font.NORMAL);
		
		font.setSize(FONT_SIZE);
		
		doc.add(new Paragraph("\n"));
		
		return font;
	}
	
}
