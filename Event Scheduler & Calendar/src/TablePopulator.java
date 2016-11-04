import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JTable;


public class TablePopulator {

	private final JTable table;
	
	private int selectedRow;
	
	public TablePopulator(JTable table) {
		
		this.table = table;
		
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void populate(Date date) {
		//TODO retrieve info from db
		LocalDate ld = DatabaseManager.parseDate(date);
		
		System.out.println("It works! " + ld.toString());
	}

	
	
}
