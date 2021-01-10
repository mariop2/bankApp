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

<% session.setAttribute("value", "updateCustomerScreen"); %>

<div><h3>To update profile information, change values below and submit</h3></div>
<form action = "webServlet" method = "post">
	
	<div>CustomerID : <%= session.getAttribute("CustomerUpdateCustID") %></div>
	<div>Name : <input type="text" name="name" value = <%= session.getAttribute("CustomerUpdateName") %>></div>
	<div>Address : <input type="text" name="address" value = <%= session.getAttribute("CustomerUpdateAddress") %>></div>
	<div>Age : <input type="number" name="age" value = <%= session.getAttribute("CustomerUpdateAge") %>></div>
	<button class="button2" type="submit"   name = "regBtns" value = "update">Submit</button>
    <button class="button2" type = "submit" name = "regBtns" value = "mainmenu">Main Menu</button>  
</form>


</body>
</html>