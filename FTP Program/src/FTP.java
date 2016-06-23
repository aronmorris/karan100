import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTP {

	public static void main(String[] args) {
		
		FTPClient ftp = new FTPClient();
		
		//in case default configuration doesn't work
		FTPClientConfig config = new FTPClientConfig();
		
		ftp.configure(config);
		
		@SuppressWarnings("unused")
		boolean err = false;
		
		try {
			
			int reply;
			
			String server = args[0];
			
			ftp.connect(server, 21); //connect to server
			
			System.out.println("Connected to " + server + ".");
		    
			System.out.print("Code: " + ftp.getReplyString());
			
			reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				
				ftp.disconnect();
				
				System.out.println("Server rejected connection.");
				
				return; //exit program if the server rejects the connection
				
			}
			
			//actual file operations
			FTPFile[] files = ftp.listDirectories();
			
			for (FTPFile f : files) {
				System.out.println(f.getName());
			}
					
			ftp.logout();
			
		} catch(IOException e) {
			
			err = true;
			
			e.printStackTrace();
			
		} finally {
			
			if (ftp.isConnected()) {
				
				try {
					ftp.disconnect();
					
				} catch (IOException io ) {
					
				}
			}
		}
		
		System.out.println("Completed.");
		
		return;
		
	}
	
}
