<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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
/*
#custTable, #acctTable {
	display: none;
}
*/
</style>


<%@page import="java.sql.*" %>
<%@page import="java.io.PrintWriter" %>

<%  session.setAttribute("value", "displayCustomer"); %>

</head>
<body>

	<%
	
    Connection con = null;
    PrintWriter pw=response.getWriter();

	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abcd");
		
			Statement stCustomer = con.createStatement();
			Statement stAccoounts = con.createStatement();

			ResultSet rsCustomers = stCustomer.executeQuery("SELECT * FROM tbl_Customer");
			ResultSet rsAccounts = stAccoounts.executeQuery(
					"SELECT c.ID, c.NAME, c.AGE, c.CITY, c.STATE, a.ID, a.TYPE, a.BALANCE, a.STATUS FROM tbl_Customer c INNER JOIN tbl_Intermediate i ON c.ID = i.CUSTOMERID INNER JOIN tbl_Account a ON a.ID = i.ACCOUNTID");
			
	%>
	
	<form name="displayCustomer" action="webServlet" method="post">	
	<!--  
	<div class="container">
		<div class="center">
			<input class="disbtn"  onclick="tableDisplayAcct()" type="submit" value="Customers Table" name = "regBtns" >
			<input class="disbtn"  onclick="tableDisplayCust()" type="submit" value="Accounts Table"  name = "regBtns">
		</div>
	</div>
	-->
	<div id="custTable">
		<table border=1 style="text-align: center" class="customers" align=center>
			<h1 style="text-align: center">Customer Table</h1>
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
		<table border=1 align=center style="text-align: center" class="customers">
			<h1 style="text-align: center"> Accounts Table</h1>
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
	       con.close();
		}
		%>
    
    <button type="submit" name = "regBtns" value = "mainmenu">Main Menu</button>
    
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