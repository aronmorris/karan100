import java.util.Properties;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EmailSender {

	
	public static void main(String[] args) throws NamingException {
		
		EmailSender send = new EmailSender();
		
	}
	public EmailSender() throws NamingException {
		
		Properties props = new Properties();
		
		Session session = Session.getInstance(props);
		
	}
	public static void sendMail(String contents) {
		// TODO Auto-generated method stub
		
	}
	
}
