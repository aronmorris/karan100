import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;


public class DownloadTableModel extends AbstractTableModel implements Observer {

	//names for table's columns
	private static final String[] columnNames = {"URL", "Size", "Progress", "Status"};
	
	//classes for the column's respective values
	private static final Class[] columnClasses = {String.class, String.class, JProgressBar.class, String.class};
	
	private ArrayList downloadList = new ArrayList();
	
	public void addDownload(Download dl) {
		
		//observer to be alerted when download does something
		dl.addObserver(this);
		
		downloadList.add(dl);
		//notify listeners that these rows have been changed
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		
	}
	
	public Download getDownload(int row) {
		return (Download) downloadList.get(row);
	}
	
	// Remove a download from the list.
    public void clearDownload(int row) {
        downloadList.remove(row);
         
        // Fire table row deletion notification to table.
        fireTableRowsDeleted(row, row);
    }
     
    // Get table's column count.
    public int getColumnCount() {
        return columnNames.length;
    }
     
    // Get a column's name.
    public String getColumnName(int col) {
        return columnNames[col];
    }
     
    // Get a column's class.
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int col) {
        return columnClasses[col];
    }
     
    // Get table's row count.
    public int getRowCount() {
        return downloadList.size();
    }
     
    // Get value for a specific row and column combination.
    public Object getValueAt(int row, int col) {
         
        Download download = (Download) downloadList.get(row);
        switch (col) {
            case 0: // URL
                return download.getURL();
            case 1: // Size
                int size = download.getDlSize();
                return (size == -1) ? "" : Integer.toString(size);
            case 2: // Progress
                return new Float(download.getDLProgress());
            case 3: // Status
                return download.getStatus();
        }
        return "";
    }
     
  /* Update is called when a Download notifies its
     observers of any changes */
    @Override
    public void update(Observable o, Object arg) {
        int index = downloadList.indexOf(o);
         
        // Fire table row update notification to table.
        fireTableRowsUpdated(index, index);
    }
}

