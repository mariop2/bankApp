<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/web.css">
<title>Insert title here</title>
</head>
<body>
<% session.setAttribute("value", "updateCustomer"); %>

<form action = "webServlet" method = "post">
<div class="topnav">
  			<a class="active" href="customerMenu.jsp">Home</a>
  			<div class="dropdown">
  				<button class="dropbtn">Customer Management
     			 	<i class="fa fa-caret-down"></i>
    			</button>
		    <div class="dropdown-content">
		      <a href="displayCustomers.jsp">Customer Details</a>
		      <a href="customerRegistration.jsp">Customer Registration</a>
		      <a href="custUpdate.jsp">Customer Update</a>
		      <a href="custDeleteSearch.jsp">Customer Delete</a>
		    </div>
		  </div>
		  <div class="dropdown">
  				<button class="dropbtn">Account Management
     			 	<i class="fa fa-caret-down"></i>
    			</button>
		    <div class="dropdown-content">
		      <a href="accountCreate.jsp">Create Account</a>
		      <a href="accountDeleteSearch.jsp">Delete Account</a>
		    </div>
		  </div>
  	  </div>
	<div class = "flexbox">
			<h3> Note: If Page Reloads the Customer Was Not Found</h3>
	        <div class = "searchnav">SSID Search
	            <input type="number" placeholder="Enter SSID" name="ssid">
	            <button class=button2 type="submit">Search</button>
	        </div>
	        <h4>-or-</h4>
	        <div class = "searchnav">CustomerID Search
	            <input type="number" placeholder="Enter CustomerID" name="custid">
	            <button class=button2 type="submit">Search</button>
	        </div>
	</div>
</form>

<script src="./updatecus.js"></script>
</body>
</html>