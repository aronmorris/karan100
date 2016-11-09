import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TablePopulator {

	private final JTable table;
	
	private final String[] eventKeys = {"event_date", "event_time", "event_desc"};
	
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
		
		HashMap<LocalTime, HashMap<String, String>> resultSet = DatabaseManager.getEventsAtDate(ld);
		
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		mdl.setRowCount(0); //sets the table as empty so it can be freshly populated
		
		for (LocalTime eKey : resultSet.keySet()) {
			
			HashMap<String, String> event = resultSet.get(eKey);
			
			mdl.addRow(retrieveData(event));
			
		}
		
		//System.out.println("It works! " + ld.toString());
	}

	private Vector<String> retrieveData(HashMap<String, String> map) {
		
		Vector<String> vector = new Vector<String>();
		
		for (String key : eventKeys) {
			vector.add(map.get(key));
		}
		
		System.out.println(vector.toString());
		
		return vector;
		
	}
	
}
