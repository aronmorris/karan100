import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


public class TablePopulator implements TableModelListener, ListSelectionListener {

	private final JTable table;
	
	private int selectedRow;
	
	public TablePopulator(JTable table) {
		
		this.table = table;
		
	}

	@Override
	public void tableChanged(TableModelEvent tme) {
		
		System.out.println("Table changed");
		
		//ResultSet res = DatabaseManager.getEventsAtDate(date);
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		
		System.out.println("List changed");
		
		selectedRow = table.getSelectedRow();
		
	}
	
}
