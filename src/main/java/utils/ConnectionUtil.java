package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		//For many frameworks using JDBC it is necessary to "register" the driver
		//in order for the framework to be aware of it.
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://java-react-210614.cick2vd0ix6h.us-east-2.rds.amazonaws.com:5432/proj1";
		String username = "postgres";  //It is possible to use env. variables to hide this information
		String password = "drowssap123";  // you would access them with System.getenv("var-name");
		
		return DriverManager.getConnection(url,username,password);
		
		
	}
}
