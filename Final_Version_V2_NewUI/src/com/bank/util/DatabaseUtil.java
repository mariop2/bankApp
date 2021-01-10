package com.bank.util;
import java.sql.*;

public class DatabaseUtil {
	
	public static Connection getConnection() {
			
			Connection con = null;
			
			try {
				System.out.println("****** here");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "12345");
				System.out.println("Conn: " + con);
			} 
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			return con;
		}
		
		public static void closeConnection(Connection con) {
			if(con != null) {
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}	
}
