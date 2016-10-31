import java.util.Calendar;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * CalendarPopulator is responsible for refreshing the calendar frames in the 
 * main calendar panel. Duty of populating the info in each of those frames
 * (1 per day in each month) is also handled by this class, which reads info
 * for each date from the DatabaseManager
 * @author Aron
 *
 */
public class CalendarPopulator {

	private final JPanel calendarPanel;
	
	private JDatePickerImpl picker;
	private UtilDateModel model;
	
	public CalendarPopulator(JPanel calendarPanel) {
		this.calendarPanel = calendarPanel;
		
		model = new UtilDateModel();
		
		model.setDate(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_YEAR);
		
		Properties prop = new Properties();
		
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
		
		
	}
	
	
}
