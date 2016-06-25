import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class FTP {

	/*
	 * args[0] == upload or download
	 * args[1] == server to connect to
	 * args[2] == file to download
	 * args[3] == path to place it
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
	
		FTPClient ftp = connect(args[1]);
		FileInputStream fis = null;
		String fileName = args[2];
		
		try {
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE, FTPClient.BINARY_FILE_TYPE);
			ftp.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
			
			System.out.println("Attempting to upload " + fileName);
			
			fis = new FileInputStream(fileName);
			
			ftp.storeFile(fileName, fis);
			
			System.out.println("Uploading file...");
			
			fis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			disconnect(ftp);
			System.out.println("Completed!");
		}
		
	}

	public static void download(String[] args) {
		
		FTPClient ftp = connect(args[1]);
		
		String fileName = args[2];
			
		//actual file operations
		FTPFile[] files = null;
		try {
			files = ftp.listFiles("/");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(ftp.getReplyString());
		
		boolean found = false;
		
		if (files != null) {
		
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
		}
		
		if (!found) {
			System.out.println("No file found with that name.");
		}
		
		disconnect(ftp);
									
		System.out.println("Completed.");
		
		return;
		
	}
	
	public static FTPClient connect(String serverURL) {

		FTPClient ftp = new FTPClient();
		
		//in case default configuration doesn't work
		FTPClientConfig config = new FTPClientConfig();
		
		ftp.configure(config);
		
		@SuppressWarnings("unused")
		boolean err = false;
		
		try {
			
			int reply;
						
			String server = serverURL;
			
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
				
				return null; //exit program if the server rejects the connection
				
			}
				
		} catch (IOException e) {
			err = true;
			e.printStackTrace();
		}
		return ftp;
	}
	
	public static void disconnect(FTPClient ftp) {
		try {
			ftp.logout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
