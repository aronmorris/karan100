import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class ProgressRenderer extends JProgressBar implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -588998258059638338L;

	public ProgressRenderer(int min, int max) {
		super(min, max); //passes off args to JProgressBar
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelect, boolean hasFocus, int row, int column) {
		//set the value of completeness as a %age
		setValue((int) ((Float) value).floatValue()); //set percentage complete value
		
		return this;
	}

	
	
}
