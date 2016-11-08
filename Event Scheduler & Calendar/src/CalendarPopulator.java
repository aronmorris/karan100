import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.swingx.JXDatePicker;





/**
 * CalendarPopulator is responsible for refreshing the calendar frames in the 
 * main calendar panel. Duty of populating the info in each of those frames
 * (1 per day in each month) is also handled by this class, which reads info
 * for each date from the DatabaseManager
 * 
 * Update: Panels are now handled by the JXDatePicker library
 * This class still handles populating the JList with event data
 * @author Aron
 *
 */

public class CalendarPopulator extends JPanel implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8452668545082745134L;
	
	
	private JXDatePicker picker;

	private Date date;
	
	private TablePopulator tablePopulator;
	
	public CalendarPopulator(JTable table) {
		picker = new JXDatePicker();
		
		tablePopulator = new TablePopulator(table);
		
		picker.getEditor().addFocusListener(this);
		
		add(picker);
		
	}
	
	public boolean hasDate() {
		return (date != null);
	}
	//returns the date picked
	public Date getDate() {
		return date;
	}
	 //focus is gained when the user clicks on a date
	@Override
	public void focusGained(FocusEvent fe) {
		date = picker.getDate();
		
		if (date != null) {
			tablePopulator.populate(date);
		}
	}

	@Override
	public void focusLost(FocusEvent fe) {
		date = picker.getDate();
		
		if (date != null) {
			tablePopulator.populate(date);
		}
	}
	
}
