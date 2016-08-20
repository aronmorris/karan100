import java.net.URL;
import java.util.Observable;

//based on http://www.java-tips.org/java-se-tips-100019/15-javax-swing/1391-how-to-create-a-download-manager-in-java.html

enum STATUS {
	DOWNLOADING(0),
	PAUSED(1),
	COMPLETE(2),
	CANCELLED(3),
	ERROR(4);
	
	private int status;
	
	private STATUS(int status) {
		this.status = status;
	}
}

public class Download extends Observable implements Runnable {

	//max size of buffer stream
	private static final int MAX_BUFFER_SIZE = 1024;
	//the download url
	private URL dlURL;
	
	private int dlSize; //size of download in bytes
	private int bytesDLed; //number of bytes downloaded
	private STATUS status; //download status
	
	
	public Download(URL url) {
		dlURL = url;
		
		setDlSize(-1);
		
		bytesDLed = 0;
		
		status = STATUS.DOWNLOADING;
		
		//the download begins immediately once declared
		download();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public int getDlSize() {
		return dlSize;
	}

	public void setDlSize(int dlSize) {
		this.dlSize = dlSize;
	}

}
