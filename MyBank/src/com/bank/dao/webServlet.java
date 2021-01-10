package com.bank.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.controller.dbController;
import com.bank.util.DatabaseUtil;


@WebServlet("/webServlet")
public class webServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con = null;
       
  
    public webServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    		
		//Connection con = DatabaseUtil.getConnection();
		//System.out.println(con);
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//know what page we are on
		HttpSession session = request.getSession();
		String pageValue = (String)session.getAttribute("value");
		
		Connection con = DatabaseUtil.getConnection();
		
		//Controls the executive Login
		if(pageValue.equalsIgnoreCase("execLogIn")) {
			
			//pull parameters from jsp
			String uName = request.getParameter("user");
			String password = request.getParameter("pass");
			
			boolean exists = false;
			
			//call method
			try {
				exists = dbController.isValidCredentials(Integer.parseInt(uName), password, con);
			} 
			catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			} 
			catch (SQLException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			//redirect to new pages
			if(exists) {
				response.sendRedirect("customerMenu.jsp");
			} 
			else {

				PrintWriter pw=response.getWriter();
				
				RequestDispatcher rd=request.getRequestDispatcher("executiveLogin.jsp");
				rd.include(request, response);
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Invalid Username or Password');");
				pw.println("</script>");
			}

		
		} 
		else if(pageValue.equalsIgnoreCase("custRegistration")) {   //Controls creation of a new Customer
			
			//Checks to see what button was pressed
			String act = request.getParameter("regBtns");
			if (act == null) {
			    //no button has been selected
			} else if (act.equals("submit")) {
			    //submit button was pressed
				String customerId = request.getParameter("customerId");
				String customerName = request.getParameter("customerName");
				String customerAge = request.getParameter("customerAge");
				String adr1 = request.getParameter("customerAddress1");
				String adr2 = request.getParameter("customerAddress2");
				String city = request.getParameter("customerCity");
				String state = request.getParameter("customerState");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.println(dtf.format(now));  
				
				try {
					dbController.createCustomer(Integer.parseInt(customerId), customerName, Integer.parseInt(customerAge), adr1, adr2, city, state, null, null, dtf.format(now), con);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				response.sendRedirect("testSuccess.jsp");
				
			} else if (act.equals("mainmenu")) {
			    //main menu button was pressed
				response.sendRedirect("mainMenu.jsp");
			} else {
			    //someone has altered the HTML and sent a different value!
			}
			
		}
		else if(pageValue.equalsIgnoreCase("custMenu")) {
			
			String x = request.getParameter("sub");
			
			if(x.equals("Show Customers")) {
				System.out.println("Show Customers");
				response.sendRedirect("displayCustomers.jsp");
				
			}
			else if(x.equals("Create Customer")) {
				System.out.println("Create Customer");
				response.sendRedirect("CustomerRegistration.jsp");
			}
			else if(x.equals("Update Customer")) {
				System.out.println("Update Customer");
				response.sendRedirect("custUpdate.jsp");
			}	
			else if(x.equals("Delete Customer")) {
				System.out.println("Delete Customer");
			}	
		}
		
		else if(pageValue.equalsIgnoreCase("updateCustomer")) {
			
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
					q = "SELECT * from tbl_Customer where id = ?";
				}
				else {
					// means ssid was searched for
					queryfor = ssid;
					q = "SELECT * from tbl_Customer where id = ?";
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
						//session.setAttribute("CustomerUpdateSSID", results.getInt(1));
						session.setAttribute("CustomerUpdateCustID", results.getInt(1));
						session.setAttribute("CustomerUpdateName", results.getString(2));
						session.setAttribute("CustomerUpdateAge", results.getInt(3));
						session.setAttribute("CustomerUpdateAddress", results.getString(4));
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
		
		else if(pageValue.equalsIgnoreCase("updateCustomerScreen")) {
						
			String q = "UPDATE tbl_Customer SET name = ?, ADR1 = ?, age = ? WHERE id = ?";
			int social = (Integer)session.getAttribute("CustomerUpdateCustID");
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
