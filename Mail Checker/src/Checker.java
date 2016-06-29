import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.util.MailLogger;

public class Checker {
	
	public static void main(String args[]) {
		
		Checker inbox = new Checker();
		
		/*
		Scanner sc = new Scanner(System.in);
		
		String protocol = sc.next();
		
		String user = sc.nextLine();
		
		String pw = sc.nextLine();
		
		inbox.read(protocol, user, pw);
		
		sc.close();
		*/
		//protocol, email address, pw
		inbox.read(args[0], args[1], args[2]);
	}
	
	public void read(String protocol, String address, String password) {
		
		Properties p = new Properties();
		
		try {
			
			p.setProperty("mail.host", "127.0.0.1");
			p.setProperty("mail.port", "25");
			p.setProperty("mail.protocol", "smtps");
			p.setProperty("mail.smtps.starttls.enable", "true");
			p.setProperty("mail.smtps.auth", "true");
			p.setProperty("mail.smtp.timeout", "10000");
			
			//p.setProperty("mail.username", address);
			//p.setProperty("mail.password", password);
			
			System.out.println("Establishing connection...");
			
			Session ses = Session.getDefaultInstance(p, null);
			
			Store store = ses.getStore(protocol);
			store.connect("smtp.gmail.com", address, password);
			
			System.out.println("Connected");
			
			if (protocol.equalsIgnoreCase("imap")) {
				readIMAP(store);
			}
			else if (protocol.equalsIgnoreCase("pop3")) {
				
			} else {
				//no protocol match
				System.out.println("No protocol specified");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//read inbox using imap protocol
	private void readIMAP(Store store) {
		
		try {
			Folder inbox = store.getFolder("inbox");
			
			System.out.println("Retrieving mail...");
			inbox.open(Folder.READ_ONLY);
			
			int count = inbox.getMessageCount();
			
			Message messages[] = inbox.getMessages();
			System.out.println(count + " total messages.");
			
			
			
			for (int i = 0; i < count; i++) {
				System.out.println("Subject: " + messages[i].getSubject());
			}
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//read inbox using pop3 protocol
	private void readPOP3(Store store) {
		
	}

}
