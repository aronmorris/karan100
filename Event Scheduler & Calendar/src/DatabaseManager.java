import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class DatabaseManager {
	
	private static String mysqlUsername;
	private static String mysqlPassword;
	
	private static String dbName = "java_test";
	private static String table = ".event_scheduler";
	
	public static void main(String[] args) {
	
		//pw & user sanitized out
		DatabaseManager.setCredentials("--", "--");
		
		DatabaseManager.addEventAtDate(LocalDate.now(), LocalTime.now(), "Testing mysql queries, should probably buy a book or something for this");
		
	}
	
	//Unsure of how to handle key replacement vs insertion with one query command, using this
	//in the meanwhile though it's inefficient
	//Updating once possible
	//Updated: Now creates a new record for every event using MySQL's uuid() function in a table that
	//allows for multiple events per day
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
			
			read(stmt);
			
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
	
	
	private static void read(Statement stmt) throws SQLException { 
		String query = "SELECT * FROM " + dbName + table;
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			System.out.println(rs.getString("uuid_pk") + " | " + rs.getString("event_date") + " | " + rs.getString("event_time") + " | " + rs.getString("event_desc"));
		}
	}
	/*
	Statement stmt = con.createStatement();
	
	String query = "SELECT id_date, event_scheduled FROM " + dbName + ".event_scheduler";
	
	ResultSet rs = stmt.executeQuery(query);
	
	while (rs.next()) {
		System.out.println(rs.getString("id_date") + " | " + rs.getString("event_scheduled"));
	}
	*/
	
}
