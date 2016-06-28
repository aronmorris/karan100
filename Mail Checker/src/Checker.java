import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;


public class Checker {
	
	public static void main(String args[]) {
		
		Checker inbox = new Checker();
		
		
		
		inbox.read(args[0], args[1], args[2]);

	}
	
	public void read(String protocol, String address, String password) {
		
		Properties p = new Properties();
		
		try {
			
			p.load(new FileInputStream(new File("C:\\smtp.properties")));
			
			Session ses = Session.getDefaultInstance(p, null);
			
			Store store = ses.getStore(protocol);
			store.connect("smtp.gmail.com", address, password);
			
			if (protocol.equalsIgnoreCase("imap")) {
				
			}
			else if (protocol.equalsIgnoreCase("pop3")) {
				
			} else {
				//no protocol match
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//read inbox using imap protocol
	private void readIMAP(Store store) {
		
	}
	//read inbox using pop3 protocol
	private void readPOP3(Store store) {
		
	}

}
