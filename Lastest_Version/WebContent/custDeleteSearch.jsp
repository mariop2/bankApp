<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Customer</title>
</head>
<body>
<% session.setAttribute("value", "custDeleteSearch"); %>

<form action = "webServlet" method = "post">
	<div class = "flexbox">
	        <div class = "searchnav">CustomerID Search
	            <input type="number" placeholder="Enter CustomerID/SSID" name="custid">
	            <button type="submit">Search</button>
	        </div>
	</div>
</form>
</body>
</html>