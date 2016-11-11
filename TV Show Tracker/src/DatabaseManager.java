import java.sql.*;

/**
 * This project will track TV shows from a url or web api and alert the user thru email
 * when a tracked show is coming up
 * @author Aron
 *
 */
public final class DatabaseManager {

	private static Connection connection;
	
	private final static String dbName = "java_test.tv_tracker"; //TODO use this name in the schema
	
	//if a connection exists, return that one.
	//If there is no connection, login to the server the old-fashioned way.
	//Significantly less gnarly than before when connections would open and close all over
	//the place and reduce performance
	public static Connection connect(String user, String password) throws ClassNotFoundException, SQLException {
		if (connection != null) {
			return connection;
		}
		else {
			
			String driverName = "org.gjt.mm.mysql.Driver";
			String serverName = "localhost:3306";
			String url = "jdbc:mysql://" + dbName + "?autoReconnect=true&useSSL=false";
			
			Class.forName(driverName);
			
			connection = DriverManager.getConnection(url, user, password);
			
			return connection;
			
		}
	}
	
}
