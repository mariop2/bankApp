package com;

import java.io.IOException;
import java.lang.*;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class webServlet
 */
@WebServlet("/HelloW")
public class HelloW extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloW() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "12345";
		
		//loading driver
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//getting connection
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(con);
    	
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//know what page we are on
		HttpSession session = request.getSession();
		String pageValue = (String)session.getAttribute("value");
		
		//used for testing getting values from page, calling a method, and redirecting to a new page if condition is satisfied
		
		if(pageValue.equalsIgnoreCase("execLogIn")){
			
			//check database for account
			String uName = request.getParameter("uname");
			String password = request.getParameter("psw");
			
			boolean exists = false;
			/*
			try {
				exists = dbController.isValidCredentials(Integer.parseInt(uName), password, con);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			*/
			if(exists){
				response.sendRedirect("testSuccess.jsp");
			}else{
				response.sendRedirect("testFailed.jsp");
			}	
		
		}
		
		
		if(pageValue.equalsIgnoreCase("updateCustomer")) {
			
			// Gets both ssid and custid inputs
			String a = request.getParameter("ssid");
			String b = request.getParameter("custid");
			
			// Converts both inputs to integers
			int ssid = a.length() == 0 ? 0 : Integer.parseInt(a);
			int custid = b.length() == 0 ? 0 : Integer.parseInt(b);
			
			// Checks which input was put in
			if(ssid == 0 && custid == 0) { 
				response.sendRedirect("custUpdate.jsp"); // if nothing inputted do not redirect to new page
			}
			else {
				int queryfor;
				String q;
				if(ssid == 0) {
					// means custid was searched for
					queryfor = custid;
					q = "SELECT * from customer where customerid = ?";
				}
				else {
					// means ssid was searched for
					queryfor = ssid;
					q = "SELECT * from customer where ssid = ?";
				}
				PreparedStatement pst;
				ResultSet results;
				int isWrong = 0;
				try{
					pst = con.prepareStatement(q);
					pst.setInt(1, queryfor);
					results = pst.executeQuery();
					while(results.next()) {
						// Set attributes to be used in JSP file
						session.setAttribute("CustomerUpdateSSID", results.getInt(1));
						session.setAttribute("CustomerUpdateCustID", results.getInt(2));
						session.setAttribute("CustomerUpdateName", results.getString(3));
						session.setAttribute("CustomerUpdateAddress", results.getString(4));
						session.setAttribute("CustomerUpdateAge", results.getInt(5));
						isWrong = 1;
					}
				}
			    catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
			    }
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				if(isWrong == 1) {
					response.sendRedirect("custUpdateScreen.jsp");
				}
				else {
					response.sendRedirect("custUpdate.jsp");
				}
			}
			
		}
		
		if(pageValue.equalsIgnoreCase("updateCustomerScreen")) {
						
			String q = "UPDATE customer SET name = ?, address = ?, age = ? WHERE ssid = ?";
			int social = (Integer)session.getAttribute("CustomerUpdateSSID");
			int age = Integer.parseInt(request.getParameter("age"));
		
			try {
				PreparedStatement pst = con.prepareStatement(q);
				pst.setString(1, request.getParameter("name"));
				pst.setString(2, request.getParameter("address"));
				pst.setInt(3, age);
				pst.setInt(4, social);	
				pst.execute();
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
		    }
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			response.sendRedirect("custUpdate.jsp");
		}
		
	}
}