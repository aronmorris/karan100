import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
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
		
		dlSize = -1;
		
		bytesDLed = 0;
		
		status = STATUS.DOWNLOADING;
		
		//the download begins immediately once declared
		download();
		
	}
	
	//the action of the download is encapsulated in its own thread, defined by the run() method of this class
	private void download() {
		Thread thread = new Thread(this);
		thread.start();
		
	}

	@Override
	public void run() {
	
		RandomAccessFile file = null;
        InputStream stream = null;
		
		try {
			HttpURLConnection connection = (HttpURLConnection) dlURL.openConnection();
			//specify which chunk of file to download
			connection.setRequestProperty("Range", "bytes=" + bytesDLed + "-");
			
			connection.connect();
			
			//ensure connection is in 200 range (200 == OK)
			if (connection.getResponseCode() / 100 != 2) {
				error(); //set download state to error
			}
			
			int contentLength = connection.getContentLength();
			if (contentLength < 1) {
				error(); //set download as errored if there's nothing being downloaded
			}
		
			/* Set the size for this download if it
         	hasn't been already set. */
            if (dlSize == -1) {
                dlSize = contentLength;
                stateChanged();
            }
             
            // Open file and seek to the end of it.
            file = new RandomAccessFile(getFileName(dlURL), "rw");
            file.seek(bytesDLed);
             
            stream = connection.getInputStream();
            while (status == STATUS.DOWNLOADING) {
            	/* Size buffer according to how much of the
           		file is left to download. */
                byte buffer[];
                if (dlSize - bytesDLed > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[dlSize - bytesDLed];
                }
                 
                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;
                 
                // Write buffer to file.
                file.write(buffer, 0, read);
                bytesDLed += read;
                stateChanged();
            }
             
            /* Change status to complete if this point was
         	reached because downloading has finished. */
            if (status == STATUS.DOWNLOADING) {
                status = STATUS.COMPLETE;
                stateChanged();
            }
	            
		} catch (IOException e) {
			System.err.println("Bad connection.");
			e.printStackTrace();
		} catch(Exception e) {
			error();
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void pause() {
		status = STATUS.PAUSED;
	}
	
	public void resume() {
		status = STATUS.DOWNLOADING;
	}
	
	public void cancel() {
		status = STATUS.CANCELLED;
	}
	
	public void error() {
		status = STATUS.ERROR;
	}
	
	private void stateChanged() {
		setChanged();
		notifyObservers();
	}
	
	private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
	}
	
	
	//return the size of the file being downloaded
	public int getDlSize() {
		return dlSize;
	}

	//returns the download progress as a percentage of the total
	public float getDLProgress() {
		return ((float) bytesDLed / dlSize) * 100;
	}

	public STATUS getStatus() {
		return status;
	}

	public URL getURL() {
		return dlURL;
	}


}
