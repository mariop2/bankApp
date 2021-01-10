<%@page import="com.bank.util.DatabaseUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css">
<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>

<style>
.customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

.customers td, .customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

.customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

.customers tr:nth-child(odd) {
	background-color: lightgray;
}

.customers tr:hover {
	background-color: #ddd;
}

.customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
	text-align: center; 
}

.container { 
  height: 200px;
  position: relative;
}

.center {
	margin: 0;
	position: absolute;
	top: 20%;
	left: 20%;
	-ms-transform: translate(-20%, -20%);
	transform: translate(-20%, -20%);
}

#custTable, #acctTable {
	display: none;
}

</style>

<%@page import="java.sql.*" %>
<%@page import="java.io.PrintWriter" %>


<% session.setAttribute("value", "custMenu"); %>

</head>
<body>

	<%
		Connection con = null;

		try {
	        PrintWriter pw=response.getWriter();

	        //Connection con = DatabaseUtil.getConnection();
			//pw.print("it's me: " + con);

			Class.forName("oracle.jdbc.driver.OracleDriver");
        	 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hello1234");
		
			
			Statement stCustomer = con.createStatement();
			Statement stAccoounts = con.createStatement();

			ResultSet rsCustomers = stCustomer.executeQuery("SELECT * FROM tbl_Customer");
			
			pw.print("\n--------------------------------: " + rsCustomers.next());
			
			ResultSet rsAccounts = stAccoounts.executeQuery(
					"SELECT c.ID, c.NAME, c.AGE, c.CITY, c.STATE, a.ID, a.TYPE, a.BALANCE, a.STATUS FROM tbl_Customer c INNER JOIN tbl_Intermediate i ON c.ID = i.CUSTOMERID INNER JOIN tbl_Account a ON a.ID = i.ACCOUNTID");
			pw.print("\nit's gelll: " + con);
			
			pw.print("\n++++++++++++++++++++++++++++++++++++++++++: " + rsAccounts.next());


			
	%>

	<form name="custMenu" action="webServlet" method="post">	
	<h1>Table</h1>
	<div class="container">
		<div class="center">
			<input class="disbtn" onclick="tableDisplayAcct()" type="submit" value="Customers Table" >
			<input class="disbtn" onclick="tableDisplayCust()" type="submit" value="Accounts Table" >
			<input class="disbtn" type="submit" value="Create Customer" name="sub">
			<input class="disbtn" type="submit" value="Update Customer" name="sub">
			<input class="disbtn" type="submit" value="Delete Customer" name="sub">
			<input class="disbtn" type="submit" value="Add Account" name="sub">
			<input class="disbtn" type="submit" value="Delete Account" name="sub">
		</div>
	</div>
	
	<h2>Still</h2>
	<div id="custTable">
	<h2>Yes</h2>
		<table border=1 style="text-align: center" class="customers" align=center>
			<thead>
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>AGE</th>
					<th>ADR1</th>
					<th>ADR2</th>
					<th>CITY</th>
					<th>STATE</th>
				</tr>
			</thead>
			<tbody>
				<% while (rsCustomers.next()) { %>
				<tr>
					<td><%=rsCustomers.getInt(1)%></td>
					<td><%=rsCustomers.getString(2)%></td>
					<td><%=rsCustomers.getInt(3)%></td>
					<td><%=rsCustomers.getString(4)%></td>
					<td><%=rsCustomers.getString(5)%></td>
					<td><%=rsCustomers.getString(6)%></td>
					<td><%=rsCustomers.getString(7)%></td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<br>
	</div>

	<div id="acctTable">
		<table border=1 align=center style="text-align: center"
			class="customers">
			<thead>
				<tr>
					<th>CUSTOMER ID</th>
					<th>CUSTOEMR NAME</th>
					<th>CUSTOEMR AGE</th>
					<th>CUSTOEMR CITY</th>
					<th>CUSTOEMR STATE</th>
					<th>ACCOUNT ID</th>
					<th>ACCOUNT TYPE</th>
					<th>ACCOUNT BALANCE</th>
					<th>ACCOUNT STATUS</th>
				</tr>
			</thead>
			<tbody>
				<% while (rsAccounts.next()) { %>
				<tr>
					<td><%=rsAccounts.getInt(1)%></td>
					<td><%=rsAccounts.getString(2)%></td>
					<td><%=rsAccounts.getInt(3)%></td>
					<td><%=rsAccounts.getString(4)%></td>
					<td><%=rsAccounts.getString(5)%></td>
					<td><%=rsAccounts.getInt(6)%></td>
					<td><%=rsAccounts.getString(7)%></td>
					<td>$<%=rsAccounts.getInt(8)%></td>
					<td><%=rsAccounts.getString(9)%></td>
				</tr>
				<% } %>	
			</tbody>
		</table>
		<br>
	</div>	
		<% } catch (Exception e) {
		e.printStackTrace();
		%> 
		<% }finally {
	       DatabaseUtil.closeConnection(con);
		}
		%>
	</form>	
		
	
	

	<script>
		function tableDisplayCust() {
			var cust = document.getElementById("custTable");
			var acct = document.getElementById("acctTable");
			cust.style.display = "block";
			acct.style.display = "none";
		}
	</script>

	<script>
		function tableDisplayAcct() {
			var cust = document.getElementById("custTable");
			var acct = document.getElementById("acctTable");
			acct.style.display = "block";
			cust.style.display = "none";
		}
	</script>


</body>
</html>