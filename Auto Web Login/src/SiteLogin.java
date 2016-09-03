import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class SiteLogin {

	private WebClient webClient;
	
	private HtmlPage splashPage;
	
	private HtmlForm loginForm;
	
	private String username, password;
	
	
	public SiteLogin(String site, String user, String pass) {
		webClient = new WebClient(BrowserVersion.CHROME);
		
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		
		username = user;
		
		password = pass;
		
		int tries = 0;
		
		do {
			
			try {
				
				splashPage = webClient.getPage(site);
				
				loginForm = (HtmlForm) splashPage.getElementById("login_form");
				
				break;
				
			} catch (FailingHttpStatusCodeException e) {
				
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tries++; //tries is incremented every time a connection is failed
			
		} while(tries < 10);
		
	}
	
	public HtmlPage login() {
		final HtmlSubmitInput button = loginForm.getInputByValue("Log In"); //get an input tagged item by its value
		
		final HtmlTextInput userField = loginForm.getInputByName("email"); //get the text field that is the username
		
		final HtmlPasswordInput passwordField = loginForm.getInputByName("pass"); //the password field
		
		userField.setValueAttribute(username);
		
		passwordField.setValueAttribute(password);
		
		try {
			final HtmlPage loggedIn = button.click();
			
			webClient.waitForBackgroundJavaScript(30000); //wait to allow things to load in before failing immediately
			
			return loggedIn;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		RetrievalService rServ = new RetrievalService("user", "pass");
		
		rServ.initiate(10);
		
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(rServ.running);
	
		System.exit(0);
		
	}
	
}
