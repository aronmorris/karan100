import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class Record {

	private File csvFile;
	
	private List<CSVRecord> listOfColumns; 
	
	public Record(String filePath) {
		csvFile = new File(filePath);
		
		CSVFormat format;
		
		if (filePath.contains(".xlsx")) { //excel extension
			format = CSVFormat.EXCEL;
		}
		
		else if (filePath.contains(".db")){ //sql extension
			format = CSVFormat.MYSQL;
		}
		
		else {
			format = CSVFormat.DEFAULT; //default case
		}
		
		
		listOfColumns = parse(csvFile, format);
		
	}
	
	//method loads the file into memory
	//may be disadvantageous for large files but will be ok for this toy program
	private List<CSVRecord> parse(File csv, CSVFormat fileFormat) {
		
		List<CSVRecord> returnList = null;
		
		try {
			Reader input = new BufferedReader(new FileReader(csv));
			
			CSVParser parser = new CSVParser(input, fileFormat);
			
			returnList = parser.getRecords();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnList;
		
	}
	
	
	
}
