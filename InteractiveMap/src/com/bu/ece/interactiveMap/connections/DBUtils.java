package com.bu.ece.interactiveMap.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static Connection dbConnection = null;
	
	public static Connection getConnection() throws ClassNotFoundException {
		
		try {
			if(dbConnection == null || dbConnection.isClosed()) {
			
				//InitialContext context;
				//try {
					Class.forName("com.mysql.jdbc.Driver");
					dbConnection = DriverManager.getConnection(  
					"jdbc:mysql://ec2-35-160-122-226.us-west-2.compute.amazonaws.com:3306/Nasa_Africa","root","nasaafrica");  
				
					//dbConnection = (Connection) dataSource.getConnection();
				
				//} catch (NamingException e) {
					//e.printStackTrace();
				//}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbConnection;
	}
}
