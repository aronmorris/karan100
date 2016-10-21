import java.sql.*;

public class DatabaseManager {

	public static void main(String[] args) {
		
		String driverName = "org.gjt.mm.mysql.Driver";
		
		try {
				
			Class.forName(driverName);
	
			String serverName = "localhost:3306";
			
			String dbName = "java_test";
			
			String url = "jdbc:mysql://" + serverName + "/" + dbName + "?autoReconnect=true&useSSL=false";
			
			
			String user = "--user--";
			
			String pw = "--PW--";
			
			Connection con;
			
			con = DriverManager.getConnection(url, user, pw);
		
			
			Statement stmt = con.createStatement();
			
			String query = "SELECT id_date, event_scheduled FROM " + dbName + ".event_scheduler";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				System.out.println(rs.getString("id_date") + " | " + rs.getString("event_scheduled"));
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
