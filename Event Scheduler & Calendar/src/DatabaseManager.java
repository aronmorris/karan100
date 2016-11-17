import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class DatabaseManager {
	
	private static String mysqlUsername;
	private static String mysqlPassword;
	
	private static String dbName = "java_test";
	private static String table = ".event_scheduler";

	
	/* used for testing things in this class - no longer needed
	public static void main(String[] args) {
	
		//pw & user sanitized out
		DatabaseManager.setCredentials("--", "--");
		
		//DatabaseManager.addEventAtDate(LocalDate.now(), LocalTime.now(), "testing");
		
		HashMap<LocalTime, HashMap<String, String>> event = DatabaseManager.getEventsAtDate(LocalDate.now());
		
		for (LocalTime key : event.keySet()) {
			System.out.println(event.get(key).get("event_time"));
		}
		
	}
	*/
	
	//Data structure returned is a map of maps which contains all the events on a given date
	//Specific details on each event can be accessed in the maps in order of time on that date
	//The UI has no idea of what's happening at each date, only loading info in as the user
	//clicks on a specific date from the calendar
	//This gives a lower memory load and lazily instantiates the necessary info
	public static HashMap<LocalTime, HashMap<String, String>> getEventsAtDate(LocalDate date) {
		
		HashMap<LocalTime, HashMap<String, String>> eventsAtDay = new HashMap<LocalTime, HashMap<String, String>>();
		
		//query to retrieve all events on a specific date by time, in order they occur
		String sqlQuery = "SELECT uuid_pk, event_time, event_date, event_desc FROM " + dbName + table + 
						  " WHERE event_date = \"" + date.format(DateTimeFormatter.ISO_DATE).toString() + "\"";
		
		System.out.println(date.format(DateTimeFormatter.ISO_DATE).toString());
		
		try {
			
			String[] columns = {"uuid_pk", "event_date", "event_time", "event_desc"}; //the columns in the database
			
			Statement stmt = connect().createStatement();
			
			ResultSet events = stmt.executeQuery(sqlQuery);
			
			while (events.next()) {
				
				LocalTime time = parseTime(events.getString("event_time"));
				
				HashMap<String, String> eventAtTime = new HashMap<String, String>();
				
				for (int i = 0; i < columns.length; i++) {
					
					eventAtTime.put(columns[i], events.getString(columns[i])); //put uuid_pk: uid, event_date: yyyy-mm-dd etc
					
				}
				
				eventsAtDay.put(time, eventAtTime);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return eventsAtDay;
		
	}
	
	public static LocalDate parseDate(java.util.Date d) {
		LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //from stackoverflow
		
		return date;
	}
	
	
	public static LocalTime parseTime(String timeStr) {
		
		LocalTime time = LocalTime.parse(timeStr);
		
		return time;
		
	}
	
	//Unsure of how to handle key replacement vs insertion with one query command, using this
	//in the meanwhile though it's inefficient
	//Updating once possible
	//Updated: Now creates a new record for every event using MySQL's uuid() function in a table that
	//allows for multiple events per day, likelihood of collision much lower
	public static void addEventAtDate(LocalDate date, LocalTime time, String eventDescription) {
		
		//converts date to ISO-8601 to compare to the 
		date.format(DateTimeFormatter.ISO_DATE);
		time.format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.CANADA));
		
		String eventDate = date.toString();
		String eventTime = time.truncatedTo(ChronoUnit.SECONDS).toString();
			
		try {
			Statement stmt = connect().createStatement();
			
			String sqlQuery = "INSERT INTO " + dbName + table + "(event_date, event_time, event_desc) "
							+ "VALUES (\"" + eventDate + "\", \"" + eventTime + "\", \"" + eventDescription + "\");"; //TODO database itself has been updated to new model more representative of the required task
			
			/*
			 * UUID_PK |Date      |Time    |Desc
			 * 		   |YYYY-MM-DD|HH:MM:SS|Lorem Ipsum
			 * 
			 */
			
			stmt.execute(sqlQuery);
			
			//read(stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void setCredentials(String user, String pw) {
		mysqlUsername = user;
		mysqlPassword = pw;
	}
	
	//takes the defined username and password, connecting to the MSQL server internally defined for testing purposes
	private static Connection connect() throws ClassNotFoundException, SQLException {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		String serverName = "localhost:3306";
		String url = "jdbc:mysql://" + serverName + "/" + dbName + "?autoReconnect=true&useSSL=false";
		
		Connection con = null;
		
			
		Class.forName(driverName);
			
		con = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);
	
		
		return con;
	}
	
	//for debugging, reads out the contents of the db
	private static void read(Statement stmt) throws SQLException { 
		String query = "SELECT * FROM " + dbName + table;
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			System.out.println(rs.getString("uuid_pk") + " | " + rs.getString("event_date") + " | " + rs.getString("event_time") + " | " + rs.getString("event_desc"));
		}
	}
	
}
