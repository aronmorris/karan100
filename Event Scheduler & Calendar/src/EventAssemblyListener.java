import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Assembles an event by drawing data out of the other listeners and the Populator
 * @author Aron
 *
 */
public class EventAssemblyListener implements ActionListener {

	private final CalendarPopulator populator;
	private final ButtonListener descText, timeText;
	
	public EventAssemblyListener(CalendarPopulator pop, ButtonListener textAreaDesc, ButtonListener textFieldTime) {
		populator = pop;
		this.descText = textAreaDesc;
		this.timeText = textFieldTime;
	}
	
	//assembles everything and inserts into the db
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (populator.hasDate() && timeText.hasText() && descText.hasText()) {
			
			LocalDate date = DatabaseManager.parseDate(populator.getDate());
			
			LocalTime time = DatabaseManager.parseTime(timeText.getText());
			
			String desc = descText.getText();
			
			DatabaseManager.addEventAtDate(date, time, desc); 
			
		}
		
	}
	
	

}
