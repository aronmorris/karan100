import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;





/**
 * CalendarPopulator is responsible for refreshing the calendar frames in the 
 * main calendar panel. Duty of populating the info in each of those frames
 * (1 per day in each month) is also handled by this class, which reads info
 * for each date from the DatabaseManager
 * @author Aron
 *
 */
public class CalendarPopulator extends JPanel implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8452668545082745134L;
	
	private JXDatePicker picker;

	public CalendarPopulator() {
		picker = new JXDatePicker();
		
		picker.getEditor().addFocusListener(this);
		
		add(picker);
		
	}

	@Override
	public void focusGained(FocusEvent fe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		// TODO Auto-generated method stub
		
	}
	
}
