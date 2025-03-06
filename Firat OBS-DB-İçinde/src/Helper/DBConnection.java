package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
	Connection c=null;
	public DBConnection() {}
	public Connection connDb() {
		try {
			this.c=(Connection )DriverManager.getConnection("jdbc:mysql://localhost:3306/okul?user=root&password=37510211022");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return c;
	} 
}
