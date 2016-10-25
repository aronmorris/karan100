import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DatabaseManager {
	
	private static String mysqlUsername;
	private static String mysqlPassword;
	
	private static String dbName = "java_test";
	private static String table = ".event_scheduler";
	
	public static void main(String[] args) {
	
		//pw & user sanitized out
		DatabaseManager.setCredentials("--", "--");
		
		DatabaseManager.addEventAtDate(LocalDate.now(), "Testing mysql queries, should probably buy a book or something for this");
		
		
		
	}
	
	//Unsure of how to handle key replacement vs insertion with one query command, using this
	//in the meanwhile though it's inefficient
	//Updating once possible
	public static void addEventAtDate(LocalDate date, String eventDescription) {
		
		//converts date to ISO-8601 to compare to the 
		date.format(DateTimeFormatter.BASIC_ISO_DATE);
		
		String eventDate = date.toString();
		
		try {
			Statement stmt = connect().createStatement();
			
			String getExistingDates = "SELECT id_date "
									+ "FROM " + dbName + table;
			
			ResultSet rs = stmt.executeQuery(getExistingDates);
			
			while (rs.next()) {
				if (rs.getString("id_date").equals(eventDate)) {
					replaceInDatabase(eventDate, eventDescription);
					return;
				}
			}
			
			insertToDatabase(eventDate, eventDescription);
			
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
	
	//inserts new event at a date into the database
	//date is YYYY-MM-DD format, one event per day until reformatted here and in the db
	private static void insertToDatabase(String formattedDate, String eventDescription) throws SQLException, ClassNotFoundException {
		Connection con = connect();
		
		String sqlInsert = "INSERT INTO " + dbName + table + " VALUES "
				+ "(\"" + formattedDate + "\", \"" + eventDescription + "\");";
						 
		
		Statement stmt = con.createStatement();
		
		stmt.executeUpdate(sqlInsert);
		
		read(stmt);
			
		
		
	}
	
	private static void replaceInDatabase(String formattedDate, String eventDescription) throws ClassNotFoundException, SQLException {
		
		Connection con = connect();
		
		String sqlInsert = "REPLACE INTO " + dbName + table + " VALUES "
				+ "(\"" + formattedDate + "\", \"" + eventDescription + "\");";
		
	}
	
	private static void read(Statement stmt) throws SQLException { 
		String query = "SELECT id_date, event_scheduled FROM " + dbName + ".event_scheduler";
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			System.out.println(rs.getString("id_date") + " | " + rs.getString("event_scheduled"));
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
