import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTP {

	/*
	 * args[0] == server to connect to
	 * args[1] == file to download
	 * args[2] == path to place it
	 */
	public static void main(String[] args) {
		
		if (args[0].equalsIgnoreCase("dl")) {
			download(args);
		}
		else if (args[0].equalsIgnoreCase("ul")) {
			upload(args);
		}
		else {
			System.out.println("No valid selection of \"dl\" (download) or \"ul\" (upload) as first argument");
		}
		
	}
	
	private static void upload(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public static void download(String[] args) {
		
		FTPClient ftp = new FTPClient();
		
		//in case default configuration doesn't work
		FTPClientConfig config = new FTPClientConfig();
		
		ftp.configure(config);
		
		@SuppressWarnings("unused")
		boolean err = false;
		
		try {
			
			int reply;
						
			String server = args[1];
			
			String fileName = args[2];
			
			String user = "anonymous";
			
			ftp.connect(server, 21); //connect to server
			
			ftp.enterLocalPassiveMode();
			
			//login credentials for anonymity
			ftp.login(user, user + "@" + server);
			
			System.out.println("Connected to " + server + ".");
		    
		
			
			reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				
				ftp.disconnect();
				
				System.out.println("Server rejected connection.");
				
				return; //exit program if the server rejects the connection
				
			}
			
			//actual file operations
			FTPFile[] files = ftp.listFiles("/");
			
			//System.out.println(ftp.getReplyString());
			
			boolean found = false;
			
			for (FTPFile f : files) {
			
				if (f.getName().equals(fileName)) {
					
					found = true;
					
					File file = new File(args[3]);
						
					try (FileOutputStream io = new FileOutputStream(file)) {
						
						System.out.println("Downloading file now...");
						
						ftp.retrieveFile(f.getName(), io);
						
						io.close();
					} catch (Exception e) {
						System.out.println("Something went wrong while downloading!");
					}
					
					break;
					
				}
					
			}
			
			if (!found) {
				System.out.println("No file found with that name.");
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
