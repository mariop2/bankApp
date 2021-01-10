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

<% session.setAttribute("value", "accountCreate"); %>

<h3>Enter account information below to create account</h3>
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
	<!-- <div class = "flexbox">
		<div>Customer ID: <input type="number" name="custid"></div>
		<div>Account ID: <input type="number" name="id"></div>
	    <div>Account Type: <input type="text"name="type"></div>
	    <div>Initial Deposit Amount: <input type="number"name="amount"></div>
	    <div>Account Status: <input type="text" name="status"></div>
	    <div>Account Message: <input type="text" name="message"></div>
	    <div>Today's Date: <input type="text" name="date"></div>
	    <button class="button2" type="submit">Confirm</button>
	  </div> -->
	  <div class = "flexbox">
		<div>Customer ID: <input type="text" name="custid"  placeholder="Enter Customer ID"
		title="9 digit numeric" required></div>
		<div>Account ID: <input type="text" name="id" placeholder="Enter Account ID" pattern="\d{9}"
		title="9 digit numeric" required></div>
	    <div>Account Type: 
	    <select name="type" required>
	    	<option value = "Checking"> Checking </option>
	    	<option value = "Saving">Saving</option>
	    </select>
	    <!--<input type="text"name="type"> -->
	    </div>
	    <div>Initial Deposit Amount: <input type="number"name="amount" placeholder="Enter Amount" pattern="\d" required></div>
	    <!-- Commenting out account status and message as they are overridden anyway
	    <div>Account Status: <input type="text" name="status"></div>
	    <div>Account Message: <input type="text" name="message"></div>
	    -->
	    <div>Today's Date: <input type="text" name="date" placeholder="YYYY-MM-DD" 
	    pattern="\d{4}\-\d{2}\-\d{2}" title="CCYY-MM-DD"></div>
	    <button class="button2" type="submit">Confirm</button>
	  </div>
</form>
 <div>-------------</div>
<!-- <form action="customerMenu.jsp">
<input type="submit" value="Main Menu" /> 
</form> -->

<form name="Menuform" action="customerMenu.jsp">
	<button class="button2" type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>

</body>
</html>