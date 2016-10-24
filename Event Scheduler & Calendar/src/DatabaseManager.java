import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DatabaseManager {
	
	private static String mysqlUsername;
	private static String mysqlPassword;
	
	private static String dbName = "java_test";
	private static String table = ".event_scheduler";
	
	public static void main(String[] args) {
	
		DatabaseManager.setCredentials("root", "There once was a genie with a 10 foot weenie");
		
		DatabaseManager.insertToDatabase("2016-12-10", "Event at the place");
		
		
		
	}
	
	public static void setCredentials(String user, String pw) {
		mysqlUsername = user;
		mysqlPassword = pw;
	}
	
	//takes the defined username and password, connecting to the MSQL server internally defined for testing purposes
	private static Connection connect() {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		String serverName = "localhost:3306";
		String url = "jdbc:mysql://" + serverName + "/" + dbName + "?autoReconnect=true&useSSL=false";
		
		Connection con = null;
		
		try {
				
			Class.forName(driverName);
				
			con = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);
		
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//inserts new event at a date into the database
	//date is YYYY-MM-DD format, one event per day until reformatted here and in the db
	private static void insertToDatabase(String formattedDate, String eventDescription) {
		Connection con = connect();
		
		String sqlInsert = "INSERT INTO " + dbName + table + " VALUES "
				+ "(\"" + formattedDate + "\", \"" + eventDescription + "\");";
						 
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate(sqlInsert);
			
			read(stmt);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	//formats user-provided data to the correct format to be inserted into the DB
	public static void addEventAtDate(LocalDate date, String eventDescription) {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		
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
