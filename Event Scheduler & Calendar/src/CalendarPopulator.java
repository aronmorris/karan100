import javax.swing.JPanel;

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
	
	public CalendarPopulator(JPanel calendarPanel) {
		this.calendarPanel = calendarPanel;
	}
	
}
