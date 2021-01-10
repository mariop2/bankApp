package com.bank.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
    		
		con = DatabaseUtil.getConnection();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//know what page we are on
		HttpSession session = request.getSession();
		String pageValue = (String)session.getAttribute("value");
		
		//Connection con = DatabaseUtil.getConnection();
		
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
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
				LocalDateTime now = LocalDateTime.now();  
				//System.out.println(dtf.format(now));  
				
				try {
					dbController.createCustomer(Integer.parseInt(customerId), customerName, Integer.parseInt(customerAge), adr1, adr2, city, state, null, null, dtf.format(now), con);
				
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("customerRegistration.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Customer Created Succesfully');");
					pw.println("</script>");
				
				} catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
					//System.out.println(e.getMessage());
					
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("customerRegistration.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Customer With That SSN/ID Already Exists');");
					pw.println("</script>");
				}				
			} else if (act.equals("mainmenu")) {
			    //main menu button was pressed
				response.sendRedirect("customerMenu.jsp");
			} else {
			    //someone has altered the HTML and sent a different value!
			}		
		}
		else if(pageValue.equalsIgnoreCase("displayCustomer")) {
			String x = request.getParameter("regBtns");
			
			if(x.equals("mainmenu")) {
				response.sendRedirect("customerMenu.jsp");
			}
			else if(x.equals("Customers Table")) {
				response.sendRedirect("displayCustomers.jsp");
			}
			else if(x.equals("Accounts Table")) {
				response.sendRedirect("displayCustomers.jsp");
			}

		}
		else if(pageValue.equalsIgnoreCase("custMenu")) {
			
			String x = request.getParameter("sub");
			
			if(x.equals("Show Customers")) {
				System.out.println("Show Customers");
				response.sendRedirect("displayCustomers.jsp");
			}
			if(x.equals("Create Customer")) {
				System.out.println("Create Customer");
				response.sendRedirect("customerRegistration.jsp");
			}
			else if(x.equals("Update Customer")) {
				System.out.println("Update Customer");
				response.sendRedirect("custUpdate.jsp");

			}	
			else if(x.equals("Delete Customer")) {
				response.sendRedirect("custDeleteSearch.jsp");
				System.out.println("Delete Customer");
			}
			else if(x.equals("Create Account")) {
				response.sendRedirect("accountCreate.jsp");
				System.out.println("Create account");
			}
			else if(x.equals("Delete Account")) {
				response.sendRedirect("accountDeleteSearch.jsp");
				System.out.println("Delete account");
			}
			else if(x.equals("logout")) {
				response.sendRedirect("homeLogin.jsp");
				System.out.println("logout");
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
						session.setAttribute("CustomerUpdateId", results.getInt(1));
						session.setAttribute("CustomerUpdateName", results.getString(2));
						session.setAttribute("CustomerUpdateAge", results.getInt(3));
						session.setAttribute("CustomerUpdateAdr1", results.getString(4));
						session.setAttribute("CustomerUpdateAdr2", results.getString(5));
						session.setAttribute("CustomerUpdateCity", results.getString(6));
						session.setAttribute("CustomerUpdateState", results.getString(7));
						session.setAttribute("CustomerUpdateStatus", results.getString(8));
						session.setAttribute("CustomerUpdateMsg", results.getString(9));
						session.setAttribute("CustomerUpdateLastUpdated", results.getString(10));
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
					response.sendRedirect("customerUpdateScreen.jsp");
				}
				else {
					response.sendRedirect("custUpdate.jsp");
				}
			}
			
		}
		else if(pageValue.equalsIgnoreCase("customerUpdateScreen")) {
			
			String act = request.getParameter("regBtns");
			
			if (act.equals("update")) {
				boolean updated = false;
				try {
					
					int customerId = (Integer) session.getAttribute("CustomerUpdateId");
					String customerName = request.getParameter("customerUpdateName");
					String customerAge = request.getParameter("customerUpdateAge");
					String adr1 = request.getParameter("customerUpdateAdr1");
					String adr2 = request.getParameter("customerUpdateAdr2");
					String city = request.getParameter("customerUpdateCity");
					String state = request.getParameter("customerUpdateState");
					String status = (String) session.getAttribute("CustomerUpdateStatus");
					String msg= "Customer Updated";

					
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
					LocalDateTime now = LocalDateTime.now();  
					System.out.println(dtf.format(now));  
					
					dbController.updateCustomer(customerId, customerName, Integer.parseInt(customerAge), adr1, adr2, city, state, status, msg, dtf.format(now), con);
					session.setAttribute("CustomerUpdateId", customerId);
					session.setAttribute("CustomerUpdateName", customerName);
					session.setAttribute("CustomerUpdateAge", customerAge);
					session.setAttribute("CustomerUpdateAdr1", adr1);
					session.setAttribute("CustomerUpdateAdr2", adr2);
					session.setAttribute("CustomerUpdateCity", city);
					session.setAttribute("CustomerUpdateState", state);
					
					updated = true;
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("customerUpdateScreen.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Error Occurd When Updating, Please Check If Information Is Correct And Correctly Formatted');");
					pw.println("</script>");
					
				}
				if(updated == true){
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("customerUpdateScreen.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('User Succesfully Updated');");
					pw.println("</script>");

					
				}
				
			}
			else {
				response.sendRedirect("customerMenu.jsp");

			}
		
		}
		else if(pageValue.equalsIgnoreCase("tellerAccountSearch")) {
			
			String a = request.getParameter("accountId");
			int accountId = a.length() == 0 ? 0 : Integer.parseInt(a);

			// Checks which input was put in
			if(accountId == 0) { 
				response.sendRedirect("tellerAccountScreen.jsp"); 
			}
			else {
				String q;

				q = "SELECT * from tbl_Account where id = ?";
				
				PreparedStatement pst;
				ResultSet results;
				int isWrong = 0;
				try{
					pst = con.prepareStatement(q);
					pst.setInt(1, accountId);
					results = pst.executeQuery();
					while(results.next()) {
						// Set attributes to be used in JSP file
						session.setAttribute("accountId", results.getInt(1));
						session.setAttribute("accountType", results.getString(2));
						session.setAttribute("accountBalance", results.getInt(3));
						session.setAttribute("accountStatus", results.getString(4));
						session.setAttribute("accountMsg", results.getString(5));
						session.setAttribute("accountLastUpdated", results.getString(6));
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
					response.sendRedirect("tellerAccountScreen.jsp");
				}
				else {
					response.sendRedirect("tellerAccountSearch.jsp");
				}
			}		
		}		
		else if(pageValue.equalsIgnoreCase("tellerAccountScreen")) {   //Controls creation of a new Customer
			
			//Checks to see what button was pressed
			String act = request.getParameter("regBtns");
			
			if (act == null) {
			    //no button has been selected
			} 
			else if(act.equals("transactionHistory")) {
				response.sendRedirect("displayTransactions.jsp");
			}		
			else if(act.equals("mainmenu")) {
				response.sendRedirect("tellerMenu.jsp");
			}
			else if (act.equals("deposit")) {
			    //submit button was pressed
				String depositWithdrawAmount = request.getParameter("DepositorWithdrawAmount");
				int dwAmount = Integer.parseInt(depositWithdrawAmount);
				Integer a = (Integer) session.getAttribute("accountId");
				int accountId = a;
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
				LocalDateTime now = LocalDateTime.now();  
				
				try {
					dbController.depositAccount(accountId, dwAmount, dtf.format(now), con);
					String q = "SELECT * from tbl_Account where id = ?";
					PreparedStatement pst = con.prepareStatement(q);
					pst.setInt(1, accountId);
					ResultSet results = pst.executeQuery();
					while(results.next()) {
						// Set attributes to be used in JSP file
			
						session.setAttribute("accountId", results.getInt(1));
						session.setAttribute("accountType", results.getString(2));
						session.setAttribute("accountBalance", results.getInt(3));
						session.setAttribute("accountStatus", results.getString(4));
						session.setAttribute("accountMsg", results.getString(5));
						session.setAttribute("accountLastUpdated", results.getString(6));
					}
					
					response.sendRedirect("tellerAccountScreen.jsp");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}		
			} else if (act.equals("withdraw")) {
			    //submit button was pressed
				String depositWithdrawAmount = request.getParameter("DepositorWithdrawAmount");
				int dwAmount = Integer.parseInt(depositWithdrawAmount);
				Integer a = (Integer) session.getAttribute("accountId");
				int accountId = a;
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
				LocalDateTime now = LocalDateTime.now();  
		
				try {
					dbController.withdrawAccount(accountId, dwAmount, dtf.format(now), con);
					String q = "SELECT * from tbl_Account where id = ?";
					PreparedStatement pst = con.prepareStatement(q);
					pst.setInt(1, accountId);
					ResultSet results = pst.executeQuery();
					while(results.next()) {
						// Set attributes to be used in JSP file
						session.setAttribute("accountId", results.getInt(1));
						session.setAttribute("accountType", results.getString(2));
						session.setAttribute("accountBalance", results.getInt(3));
						session.setAttribute("accountStatus", results.getString(4));
						session.setAttribute("accountMsg", results.getString(5));
						session.setAttribute("accountLastUpdated", results.getString(6));
					}
					
					response.sendRedirect("tellerAccountScreen.jsp");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}	
			} else if (act.equals("transferSearch")) {
				
				String a = request.getParameter("trasnferAccountId");
				int transferAccountId = a.length() == 0 ? 0 : Integer.parseInt(a);
				
				int sourceAccountId = (Integer) session.getAttribute("accountId");
			
				// Checks which input was put in
				if(transferAccountId == 0 || transferAccountId == sourceAccountId) { 
					response.sendRedirect("tellerAccountScreen.jsp"); 
				}
				else {
					String q;

					q = "SELECT * from tbl_Account where id = ?";
					
					PreparedStatement pst;
					ResultSet results;
					int isWrong = 0;
					try{
						pst = con.prepareStatement(q);
						pst.setInt(1, transferAccountId);
						results = pst.executeQuery();
						while(results.next()) {
							// Set attributes to be used in JSP file
							session.setAttribute("transferAccountId", results.getInt(1));
							session.setAttribute("transferAccountType", results.getString(2));
							session.setAttribute("transferAccountBalance", results.getInt(3));
							session.setAttribute("transferAccountStatus", results.getString(4));
							session.setAttribute("transferAccountMsg", results.getString(5));
							session.setAttribute("transferAccountLastUpdated", results.getString(6));
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
						response.sendRedirect("tellerAccountTransferScreen.jsp");
					}
					else {
						response.sendRedirect("tellerAccountScreen.jsp");
					}
				}
			}
		}	
		else if(pageValue.equalsIgnoreCase("tellerAccountTransferScreen")) {   //Controls creation of a new Customer
			
			//Checks to see what button was pressed
			String act = request.getParameter("regBtns");
			
			if (act == null) {
			    //no button has been selected
			}
			else if(act.equals("mainmenu")) {
				response.sendRedirect("tellerMenu.jsp");
			} 
			else if (act.equals("transfer")) {
			    //submit button was pressed
				String a = request.getParameter("transferAmount");
				int transferAmount = Integer.parseInt(a);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
				LocalDateTime now = LocalDateTime.now();  
				
				//transferAmount
				Integer c = (Integer) session.getAttribute("accountId");
				int accountId = c;
				
				boolean withdrawSuccess = false;
		
				try {
					withdrawSuccess = dbController.withdrawAccount(accountId, transferAmount, dtf.format(now), con);
					String q = "SELECT * from tbl_Account where id = ?";
					PreparedStatement pst = con.prepareStatement(q);
					pst.setInt(1, accountId);
					ResultSet results = pst.executeQuery();
					while(results.next()) {
						// Set attributes to be used in JSP file
			
						session.setAttribute("accountId", results.getInt(1));
						session.setAttribute("accountType", results.getString(2));
						session.setAttribute("accountBalance", results.getInt(3));
						session.setAttribute("accountStatus", results.getString(4));
						session.setAttribute("accountMsg", results.getString(5));
						session.setAttribute("accountLastUpdated", results.getString(6));
					}
					
					//response.sendRedirect("tellerAccountTransferScreen.jsp");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				Integer b = (Integer) session.getAttribute("transferAccountId");
				accountId = b;
				
				try {
					if(withdrawSuccess){
						dbController.depositAccount(accountId, transferAmount, dtf.format(now), con);
						String q = "SELECT * from tbl_Account where id = ?";
						PreparedStatement pst = con.prepareStatement(q);
						pst.setInt(1, accountId);
						ResultSet results = pst.executeQuery();
						while(results.next()) {
							// Set attributes to be used in JSP file
				
							session.setAttribute("transferAccountId", results.getInt(1));
							session.setAttribute("transferAccountType", results.getString(2));
							session.setAttribute("transferAccountBalance", results.getInt(3));
							session.setAttribute("transferAccountStatus", results.getString(4));
							session.setAttribute("transferAccountMsg", results.getString(5));
							session.setAttribute("transferAccountLastUpdated", results.getString(6));
						}
					}
					
					response.sendRedirect("tellerAccountTransferScreen.jsp");
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
		
			} 
		}
		else if(pageValue.equalsIgnoreCase("tellerMenu")) {
			String x = request.getParameter("sub");
			
//			if(x.equals("Delete Customers")) {
//				System.out.println("Delete Customers");
//				response.sendRedirect("custDeleteSearch.jsp");
//				
//			}
//			else if(x.equals("Display Transactions")) {
//				System.out.println("Display Transactions");
//				response.sendRedirect("displayTransactions.jsp");
//			}
			
			
			if(x.equals("Make Transaction")) {
				System.out.println("Make Transaction");
				response.sendRedirect("tellerAccountSearch.jsp");

			}	
			else if(x.equals("logout")) {
				System.out.println("Logout");
				response.sendRedirect("homeLogin.jsp");
			}
		}
		else if(pageValue.equalsIgnoreCase("tellerLogIn")) {
				
			//pull parameters from jsp
			String uName = request.getParameter("user");
			String password = request.getParameter("pass");
			
			boolean exists = false;
			
			//call method
			try {
				exists = dbController.isValidCredentialsTeller(Integer.parseInt(uName), password, con);
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
				response.sendRedirect("tellerMenu.jsp");
			} 
			else {

				PrintWriter pw=response.getWriter();
				
				RequestDispatcher rd=request.getRequestDispatcher("tellerLogin.jsp");
				rd.include(request, response);
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Invalid Username or Password');");
				pw.println("</script>");
			}		
		}
			
		else if(pageValue.equalsIgnoreCase("custDeleteSearch")) {
			
			// Get inputted ID & convert to int
			String b = request.getParameter("custid");
			int custid = b.length() == 0 ? 0 : Integer.parseInt(b);
			
			// If button submitted and input is blank redirect to same page
			if(custid == 0) { 
				response.sendRedirect("custDeleteSearch.jsp"); 
			}
			
			// variable for checking if query returns results
			int noResults = 0;
			
			String q = "select * from tbl_Customer where id = ?";
			try {
				PreparedStatement pst = con.prepareStatement(q);
				pst.setInt(1, custid);
				ResultSet results = pst.executeQuery();
				while(results.next()) {
					session.setAttribute("custDeleteID", results.getInt(1));
					session.setAttribute("custDeleteName", results.getString(2));
					session.setAttribute("custDeleteStatus", results.getString(8));		
					session.setAttribute("custDeleteCity", results.getString(6));
					session.setAttribute("custDeleteState", results.getString(7));
					noResults++;
				}
				if (noResults == 1){
					response.sendRedirect("custDeleteConfirm.jsp");
				}else{
					response.sendRedirect("custDeleteSearch.jsp");
				}
			}
			catch (Exception e){
				PrintWriter pw=response.getWriter();
				RequestDispatcher rd=request.getRequestDispatcher("custDeleteSearch.jsp");
				rd.include(request, response);
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Please Ensure the Entry Is Correctly Formatted');");
				pw.println("</script>");
			}		
		}
		else if(pageValue.equalsIgnoreCase("custDeleteConfirm")) {
			
			boolean exists = false;
			String button = request.getParameter("sub");
			
			if(button.equals("delete")) {
				try {
					int custDeleteId = (Integer) session.getAttribute("custDeleteID");
					exists = dbController.removeCustomerFromBank(custDeleteId, con);
					session.setAttribute("DeleteMessage", "Customer account deleted successfully!");
				}			
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				
				if(exists) {
					//response.sendRedirect("tellerMenu.jsp");
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("accountDeleteConfirm.jsp");
					rd.include(request, response);
					try{
						session.setAttribute("custDeleteID", "Customer Deleted");
						session.setAttribute("custDeleteName", null);
						session.setAttribute("custDeleteStatus", null);
						session.setAttribute("custDeleteCity", null);
						session.setAttribute("custDeleteState", null);
						response.sendRedirect("custDeleteConfirm.jsp");
					}catch(Exception e){
						
					}
				}
				else {
					//System.out.println("FAILURE TO DELETE CUSTOMER");
					//response.sendRedirect("tellerMenu.jsp");
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("custDeleteConfirm.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('The Customer Could Not Be Deleted');");
					pw.println("</script>");
				}
			}
			else if(button.equals("inactive")) {
				String q = "UPDATE tbl_Customer SET status = ? where id = ?";
				try {
					PreparedStatement pst = con.prepareStatement(q);
					pst.setString(1, "Inactive");
					int custDeleteId = (Integer) session.getAttribute("custDeleteID");
					pst.setInt(2, custDeleteId);
					pst.executeQuery();
					
					q = "select * from tbl_Customer where id = ?";
					pst = con.prepareStatement(q);
					pst.setInt(1, custDeleteId);
					ResultSet results = pst.executeQuery();
					while(results.next()) {
						session.setAttribute("custDeleteID", results.getInt(1));
						session.setAttribute("custDeleteName", results.getString(2));
						session.setAttribute("custDeleteCity", results.getString(6));
						session.setAttribute("custDeleteState", results.getString(7));
						session.setAttribute("custDeleteStatus", results.getString(8));
					}
					
					response.sendRedirect("custDeleteConfirm.jsp");
				}
				catch (Exception e) {
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("custDeleteConfirm.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Can't Inactivate A Customer That Was Deleted');");
					pw.println("</script>");
			    }
			}
			else if(button.equals("mainmenu")) {
				response.sendRedirect("customerMenu.jsp");
			}
		}
		else if(pageValue.equalsIgnoreCase("accountDeleteSearch")) {
			
			// Get inputted ID & convert to int
			String b = request.getParameter("accid");
			int accid = b.length() == 0 ? 0 : Integer.parseInt(b);
			
			// If button submitted and input is blank redirect to same page
			if(accid == 0) { 
				response.sendRedirect("accountDeleteSearch.jsp"); 
			}
			
			// variable for checking if query returns results
			int noResults = 0;
			
			try {
				String q = "select * from tbl_Account where id = ?";
				PreparedStatement pst = con.prepareStatement(q);
				pst.setInt(1, accid);
				ResultSet results = pst.executeQuery();
				while(results.next()) {
					session.setAttribute("accountDeleteID", results.getInt(1));
					session.setAttribute("accountDeleteType", results.getString(2));
					session.setAttribute("accountDeleteBalance", results.getInt(3));
					session.setAttribute("accountDeleteStatus", results.getString(4));
					noResults++;
				}
				if (noResults == 1){
					response.sendRedirect("accountDeleteConfirm.jsp");
				}else{
					response.sendRedirect("accountDeleteSearch.jsp");
				}
			}
			catch (Exception e){
				PrintWriter pw=response.getWriter();
				RequestDispatcher rd=request.getRequestDispatcher("custDeleteSearch.jsp");
				rd.include(request, response);
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Please Ensure the Entry Is Correctly Formatted');");
				pw.println("</script>");
			}
			
		}
		else if(pageValue.equalsIgnoreCase("accountDeleteConfirm")) {
			
			String button = request.getParameter("but");
			boolean exists = false;
			if(button.equals("delete")) {
				try {
					int accountDeleteId = (Integer) session.getAttribute("accountDeleteID");
					exists = dbController.removeAccountFromBank(accountDeleteId, con);
				}
				catch (Exception e){
					System.out.println(e.getMessage());
				}
				
				if(exists) {
					//response.sendRedirect("tellerMenu.jsp");
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("accountDeleteConfirm.jsp");
					rd.include(request, response);
					try{
						session.setAttribute("accountDeleteID", "Account Deleted");
						session.setAttribute("accountDeleteType", null);
						session.setAttribute("accountDeleteBalance", null);
						session.setAttribute("accountDeleteStatus", null);
						response.sendRedirect("accountDeleteConfirm.jsp");
					}catch(Exception e){
						
					}
					
				}
				else {
					//System.out.println("FAILURE TO DELETE CUSTOMER");
					//response.sendRedirect("tellerMenu.jsp");
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("accountDeleteConfirm.jsp");
					rd.include(request, response);
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('The Account Could Not Be Deleted');");
					pw.println("</script>");
				}
			}
			else if(button.equals("inactive")) {
				
				String q = "UPDATE tbl_Account set status = ? where id = ?";
				try {
					PreparedStatement pst = con.prepareStatement(q);
					pst.setString(1, "Inactive");
					int accountDeleteId = (Integer) session.getAttribute("accountDeleteID");
					pst.setInt(2, accountDeleteId);
					pst.executeQuery();
					PrintWriter pw=response.getWriter();
					RequestDispatcher rd=request.getRequestDispatcher("custDeleteConfirm.jsp");
					rd.include(request, response);
					
					q = "select * from tbl_Account where id = ?";
					pst = con.prepareStatement(q);
					pst.setInt(1, accountDeleteId);
					ResultSet results = pst.executeQuery();
					while(results.next()) {
						session.setAttribute("accountDeleteID", results.getInt(1));
						session.setAttribute("accountDeleteType", results.getString(2));
						session.setAttribute("accountDeleteBalance", results.getInt(3));
						session.setAttribute("accountDeleteStatus", results.getString(4));
					}
					
					response.sendRedirect("accountDeleteConfirm.jsp");
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
			    }
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}	
			}
			else if(button.equals("mainmenu")) {
				response.sendRedirect("customerMenu.jsp");
			}
		}		
		else if(pageValue.equalsIgnoreCase("dispTransaction")) {
			
			String x = request.getParameter("regBtns");
			
			if(x.equals("transactionHistory")) {
				System.out.println("Show Customers");
				response.sendRedirect("lastTenTransactions.jsp");	
			}
			else if(x.equals("ShowTransactions")) {
				
				session.setAttribute("startDate", request.getParameter("date1"));
				session.setAttribute("endDate", request.getParameter("date2"));
				
				System.out.println(session.getAttribute("startDate"));
				System.out.println(session.getAttribute("endDate"));

				System.out.println("Create Customer");
				response.sendRedirect("intervalTransactions.jsp");
			}
			else if(x.equals("mainmenu")) {
				System.out.println("Update Customer");
				response.sendRedirect("tellerMenu.jsp");
			}
		}
		else if(pageValue.equalsIgnoreCase("tenTransactions")) {
			
			response.sendRedirect("tellerMenu.jsp");
		}
		else if(pageValue.equalsIgnoreCase("transactionInterval")) {
			response.sendRedirect("tellerMenu.jsp");
		}
		else if(pageValue.equalsIgnoreCase("accountCreate")) {
			
			boolean exists = false;
			
			int custid = Integer.parseInt(request.getParameter("custid"));
			int id = Integer.parseInt(request.getParameter("id"));
			int balance = Integer.parseInt(request.getParameter("amount"));
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			String message = request.getParameter("message");
			String date = request.getParameter("date");
			
			try {
				exists = dbController.createAccount(id, type, balance, status, message, date, custid, con);
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
		    }
			
			if(exists) {
				response.sendRedirect("customerMenu.jsp");
			}
			else {
				System.out.println("FAILURE TO CREATE ACCOUNT");
				response.sendRedirect("customerMenu.jsp");
			}
			
		}
	}
}

