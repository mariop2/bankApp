package com.bank.initializer;

import java.sql.Connection;
import java.sql.SQLException;

import com.bank.controller.bankDB;
import com.bank.util.DatabaseUtil;

public class deleter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		//getting connection
		Connection con = DatabaseUtil.getConnection();
		
		System.out.println(con);
		
		try {
			bankDB.deleteDBTbls(con);
			con.close();
			System.out.println("Tables Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
