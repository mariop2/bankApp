<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*,java.lang.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Customer Info</title>
<link rel="stylesheet" href="./styles.css">
</head>
<body>

<% session.setAttribute("value", "updateCustomer"); %>

<form action = "HelloW" method = "post">
	<div class = "flexbox">
	        <div class = "searchnav">SSID Search
	            <input type="number" placeholder="Enter SSID" name="ssid">
	            <button type="submit">Search</button>
	        </div>
	        <div class = "searchnav">CustomerID Search
	            <input type="number" placeholder="Enter CustomerID" name="custid">
	            <button type="submit">Search</button>
	        </div>
	</div>
</form>

<script src="./updatecus.js"></script>
</body>
</html>