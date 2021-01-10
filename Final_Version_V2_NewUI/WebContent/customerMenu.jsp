<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <!--     <link rel="stylesheet" 
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
	> -->
    <link rel="stylesheet" href="./css/styles.css">
    <link rel="stylesheet" href="./css/web.css">
    <title>ABC Bank</title>
    <%  session.setAttribute("value", "custMenu"); %>

</head>
<body>
<h1>Executive Main Menu</h1>
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
		  <a class="active" href="homeLogin.jsp">Logout</a>
  	  </div>
   
	<form name = "custMenu" action="webServlet" method ="post" class="modal-content">
	<!-- <table>
		<tr>
			<td><input type="submit" value="Show Customers" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Customer" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Update Customer" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Delete Customer" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Account" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Delete Account" name="sub"></td>
		</tr>
		<tr>
			<td><input type="submit" value="logout" name="sub"></td>
		</tr>
		
	</table> -->
<div class="imgcontainer">
	    <img src="images/login.png" alt="Avatar" class="avatar">
	  </div>



</form>
</body>
</html>