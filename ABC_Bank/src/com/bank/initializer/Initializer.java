package com.bank.initializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.bank.controller.dbController;
import com.bank.util.DatabaseUtil;

public class Initializer {

	public static void main(String[] args) throws SQLException{
		
		//getting connection
		Connection con = DatabaseUtil.getConnection();
		
		System.out.println(con);
		
		/*
		 * Data Table Creation. Three tables are created, (1)Customers, (2)Accounts, (3)Intermediate Table
		 * Many to Many relationship
		 */
		String q = "CREATE TABLE tbl_Customer("
				+ "id NUMBER PRIMARY KEY,"
				+ "name VARCHAR2(45 char),"
				+ "age  NUMBER,"
				+ "adr1 VARCHAR2(80 char),"
				+ "adr2 VARCHAR2(80 char),"
				+ "city VARCHAR2(45 char),"
				+ "state VARCHAR2(20 char),"
				+ "status VARCHAR2(80 char),"
				+ "msg VARCHAR2(255 char),"
				+ "lastUpdated VARCHAR2(50 char))";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "CREATE TABLE tbl_Account("
				+ "id NUMBER PRIMARY KEY,"
				+ "type VARCHAR2(50 char),"
				+ "balance  NUMBER,"
				+ "status VARCHAR2(80 char),"
				+ "msg VARCHAR2(255 char),"
				+ "lastUpdated VARCHAR2(20 char))";
		
		pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "CREATE TABLE tbl_Intermediate("
				+ "customerId NUMBER NOT NULL,"
				+ "accountId NUMBER NOT NULL,"
				+ "FOREIGN KEY(customerId) REFERENCES tbl_Customer(id),"
				+ "FOREIGN KEY(accountId) REFERENCES tbl_Account(id))";
		
		pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "CREATE TABLE tbl_LoginCredentials("
				+ "username NUMBER PRIMARY KEY,"
				+ "password VARCHAR2(80 CHAR))";
		
		pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		boolean x;

		
		x = dbController.createExecutiveLogin(12345, "pass", con);
		System.out.println(x);
		
		
		/*
		 * Data Table Creation. Three tables are created, (1)Customers, (2)Accounts, (3)Intermediate Table
		 * Many to Many relationship		
		*/
		x = dbController.createCustomer(1, "James Anderson", 25, "fads", "fads", "New York", "NY", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(2, "Sherry Smith", 35, "fads", "fads", "Los Angeles", "CA", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(3, "David Beck", 32, "fads", "fads", "Dallas", "TX", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(4, "Randy Fox", 44, "fads", "fads", "Seattle", "WA", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(5, "Leo Perez", 24, "fads", "fads", "Boston", "MA", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(6, "Sam Bucanan", 60, "fads", "fads", "San Jose", "CA", "fads", "fads", "fads", con);
		System.out.println(x);
		x = dbController.createCustomer(7, "Frank Johnson", 50, "fads", "fads", "San Diego", "CA", "fads", "fads", "fads", con);
		System.out.println(x);
		
		
		//
		
		x = dbController.createAccount(1, "Saving", 10000, "fdasfd", "fdasdfs", "11/58/28", 1, con);
		System.out.println(x);
		x = dbController.createAccount(2, "Saving", 9000, "fdasfd", "fdasdfs", "11/58/28", 1, con);

		x = dbController.createAccount(3, "Saving", 8500, "fdasfd", "fdasdfs", "11/58/28", 2, con);

		x = dbController.createAccount(4, "Saving", 4300, "fdasfd", "fdasdfs", "11/58/28", 4, con);

		x = dbController.createAccount(5, "Checking", 5000, "fdasfd", "fdasdfs", "11/58/28", 7, con);
		System.out.println(x);
		x = dbController.createAccount(6, "Saving", 15000, "fdasfd", "fdasdfs", "11/58/28", 3, con);
		System.out.println(x);
		x = dbController.createAccount(7, "Checking", 1200, "fdasfd", "fdasdfs", "11/58/28", 3, con);
		System.out.println(x);
		
		//

		//test deleting a customer without bank account
		//x = bankController.deleteCustomer(7, con);
		//System.out.println(x);
		
		//return con;
		
	
	}
	
	public static void deleteDBTbls(Connection con) throws SQLException{
		
		System.out.println("Deleting tables........");
		
		String q = "DROP TABLE tbl_Intermediate";
		PreparedStatement pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "DROP TABLE tbl_Customer";
		pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "DROP TABLE tbl_Account";
		pst = con.prepareStatement(q);
		pst.executeUpdate();
		
		q = "DROP TABLE tbl_LoginCredentials";
		pst = con.prepareStatement(q);
		pst.executeUpdate();
				
	}

}
