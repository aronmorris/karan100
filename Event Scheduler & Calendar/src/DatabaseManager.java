import java.sql.*;
import java.time.LocalDate;

public class DatabaseManager {
	
	private static String mysqlUsername;
	private static String mysqlPassword;
	
	//takes the defined username and password, connecting to the MSQL server internally defined for testing purposes
	private Connection connect() {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		String serverName = "localhost:3306";
		String dbName = "java_test";
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
	private void insertToDatabase(String formattedDate, String eventDescription) {
		Connection con = connect();
		
		
	}
	//formats user-provided data to the correct format to be inserted into the DB
	public static void addEventAtDate(LocalDate date, String eventDescription) {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		
		
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
