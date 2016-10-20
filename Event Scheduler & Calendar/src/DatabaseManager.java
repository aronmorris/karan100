import java.sql.*;

public class DatabaseManager {

	public static void viewTable(Connection con, String dbName) {
		
		String query = "select *" +
					   "from " + dbName;
		
		 try (Statement stmt = con.createStatement()) {
		        ResultSet rs = stmt.executeQuery(query);
		        while (rs.next()) {
		            String coffeeName = rs.getString("COF_NAME");
		            int supplierID = rs.getInt("SUP_ID");
		            float price = rs.getFloat("PRICE");
		            int sales = rs.getInt("SALES");
		            int total = rs.getInt("TOTAL");
		            System.out.println(coffeeName + "\t" + supplierID +
		                               "\t" + price + "\t" + sales +
		                               "\t" + total);
		        }
		    } catch (SQLException e ) {
		        e.printStackTrace();
		}
		
	}
	
}
