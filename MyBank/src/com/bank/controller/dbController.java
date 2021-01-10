package com.bank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbController {

	
	//insert customer to table using prepared statement
	public static boolean createCustomer(int id, String name, int age, String adr1, String adr2, String city, String state, String status, String msg, String lastUpdate, Connection con) throws SQLException{
		
		String q = "INSERT INTO tbl_Customer(id , name, age, adr1, adr2, city, state, status, msg, lastUpdated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,id);
		pst.setString(2, name);
		pst.setInt(3, age);
		pst.setString(4, adr1);
		pst.setString(5, adr2);
		pst.setString(6, city);
		pst.setString(7, state);
		pst.setString(8, status);
		pst.setString(9, msg);
		pst.setString(10, lastUpdate);
		
		
		//ResultSet rs = pst.executeQuery();
		//or
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			return true;
		}else {
			return false;
		}
	}
	

	public static boolean createAccount(int id, String type, int balance, String status, String msg, String lastUpdated, int customerId, Connection con) throws SQLException{
		
		String q = "INSERT INTO tbl_Account(id , type, balance, status, msg, lastUpdated) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,id);
		pst.setString(2, type);
		pst.setInt(3, balance);
		pst.setString(4, status);
		pst.setString(5, msg);
		pst.setString(6, lastUpdated);
		
		//ResultSet rs = pst.executeQuery();
		//or
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			return addToLinkTbl(customerId, id, con);
		}else {
			return false;
		}
		
		
		
	}
	
	static boolean addToLinkTbl(int customerId, int accountId, Connection con) throws SQLException{
		
		String q = "INSERT INTO tbl_Intermediate(customerId , accountId) VALUES (?, ?)";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,customerId);
		pst.setInt(2, accountId);
		
		//ResultSet rs = pst.executeQuery();
		//or
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	//Removes the account from the customer
	static boolean removeCustomerFromAccount(int customerId, int accountId, Connection con) throws SQLException{
		
		String q = "DELETE FROM tbl_Intermediate WHERE customerId = ? AND accountId = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,customerId);
		pst.setInt(2,accountId);
		
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			//attempt to delete Account
			q = "DELETE FROM tbl_Account WHERE Id = ?";
			pst = con.prepareStatement(q);
			pst.setInt(1,accountId);
			pst.executeUpdate();
			return true;
		}else{
			return false;
		}
	
	}
	
	//Removes the account from the bank
	static boolean removeAccountFromBank(int accountId, Connection con) throws SQLException{
		
		String q = "DELETE FROM tbl_Intermediate WHERE accountId = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,accountId);
		
		pst.executeUpdate();
		
		q = "DELETE FROM tbl_Account WHERE id = ?";
		pst = con.prepareStatement(q);
		pst.setInt(1,accountId);
		
		int cnt = pst.executeUpdate();
		
		if(cnt == 1){
			return true;
			
		}else{
			return false;
		}

	}
	
	//Removes the account from the bank
	static boolean removeCustomerFromBank(int customerId, Connection con) throws SQLException{
		
		String q = "DELETE FROM tbl_Intermediate WHERE customerId = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,customerId);
		
		pst.executeUpdate();
		
		q = "DELETE FROM tbl_Customer WHERE id = ?";
		pst = con.prepareStatement(q);
		pst.setInt(1,customerId);
		
		int cnt = pst.executeUpdate();
		
		//get list of active account id's
		q = "SELECT id FROM tbl_Account";
		pst = con.prepareStatement(q);
		
		ResultSet results = pst.executeQuery();
		ResultSet results2;
		int tempAccountId;
		
		while(results.next()) {
			// check to see if link exists in intermediate table and count instances
			tempAccountId = results.getInt(1);
			q = "SELECT COUNT(*) FROM tbl_Intermediate WHERE accountId = ?";;
			pst = con.prepareStatement(q);
			pst.setInt(1, tempAccountId);
			results2 = pst.executeQuery();
			results2.next();
			//if count is zero delete from account table
			if (results2.getInt(1) == 0){
				q = "DELETE FROM tbl_Account WHERE id = ?";;
				pst = con.prepareStatement(q);
				pst.setInt(1, tempAccountId);
				pst.executeUpdate();
			}
				
		}
		
		if(cnt == 1){
			return true;
			
		}else{
			return false;
		}

	}
	
	
	

	public static boolean addCustomerToAccount(int customerId, int accountId, Connection con) throws SQLException{
		
		return addToLinkTbl(customerId, accountId, con);
		
	}
	

	public static boolean createExecutiveLogin(int username, String pwd, Connection con) throws SQLException{
		
		String q = "INSERT INTO tbl_LoginCredentials(username , password) VALUES (?, ?)";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,username);
		pst.setString(2, pwd);
		
		//ResultSet rs = pst.executeQuery();
		//or
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			
			return true;
		}else {
			return false;
		}
	}
	
	static boolean deleteCustomer(int accountId, Connection con) throws SQLException{
		
		String q = "DELETE FROM tbl_Customer WHERE id = ?";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,accountId);
		
		int cnt = pst.executeUpdate();
		if(cnt == 1){
			return true;
		}else{
			return false;
		}
		
		
		
	}
	
	public static boolean isValidCredentials(int userName, String userPwd, Connection con) throws SQLException{
		
		
		String q = "SELECT * from tbl_LoginCredentials WHERE username = ? AND password = ?";
		
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1,userName);
		pst.setString(2,userPwd);
		
		//int cnt = pst.executeUpdate();
		ResultSet results = pst.executeQuery();
		
		if (!results.isBeforeFirst() ) {    
		    System.out.println("No data"); 
		    return false;
		} else{
			return true;
		}
	
		
	}
	
	//deposit into account
	public static boolean depositAccount(int accountid, double amount, Connection con){
		
		
		
		
		return true;
	}
	
	
	
	//widthraw from account
	
	
	
	
	//create transaction history
	
	
	
	
	//getTransactionHistory
	
	

	

}
