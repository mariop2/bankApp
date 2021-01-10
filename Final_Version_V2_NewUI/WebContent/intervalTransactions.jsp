<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

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
	/* background-color: #4CAF50; */
	background-color:rgb(43, 92, 124);
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
</style>

<%@page import="java.sql.*" %>

<%  session.setAttribute("value", "transactionInterval"); %>


<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/web.css">
<title>Insert title here</title>
</head>
<body>
	
	<%
	
    Connection con = null;

	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "12345");
		
        	int accountNumber =(Integer)session.getAttribute("accountId");
        	java.sql.Date d1 = java.sql.Date.valueOf((String)session.getAttribute("startDate"));
        	java.sql.Date d2 = java.sql.Date.valueOf((String)session.getAttribute("endDate"));

        	System.out.println(">>>>> " + session.getAttribute("startDate") + "  " + session.getAttribute("endDate"));
        	
			Statement st = con.createStatement();
			String q = "SELECT * FROM tbl_Transactions where id=? and transactionDate >= ? and transactionDate <= ?";			
		
			PreparedStatement pst = con.prepareStatement(q);
			
			pst.setInt(1, accountNumber);
			pst.setDate(2, d1);
			pst.setDate(3, d2);

			ResultSet res = pst.executeQuery();

	%>
	
	<form name = "transactionInterval" action="webServlet" method ="post" class="modal-content">
	<div id="custTable">
		<table border=1 style="text-align: center" class="customers" align=center>
			<h1 style="text-align: center">Start and End Date Table</h1>
			<thead>
				<tr>
					<th>ACCOUNT ID</th>
					<th>BALANCE</th>
					<th>TYPE</th>
					<th>DESC</th>
					<th>TRANSACTION Date</th>
				</tr>
			</thead>
			<tbody>
				<% while (res.next()) { %>
				<tr>
					<td><%=res.getInt(1)%></td>
					<td>$<%=res.getInt(2)%></td>
					<td><%=res.getString(3)%></td>
					<td><%=res.getString(4)%></td>
					<td><%=res.getDate(5)%></td>
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
    
    <button class="button2" type="submit" name = "regBtns" value = "mainmenu">Main Menu</button>
    </form>
</body>
</html>