import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EmailSender {

	
	public static void main(String[] args) throws NamingException {
		
		EmailSender send = new EmailSender();
		
	}
	public EmailSender() throws NamingException {
		
		InitialContext ctx = new InitialContext();
		
		Session session = (Session) ctx.lookup("usersMailSession");
		
		System.out.println(session.toString());
		
	}
	
}
