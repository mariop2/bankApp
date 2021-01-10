<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit</title>
</head>
<body>
<% session.setAttribute("value", "tellerAccountSearch"); %>

<form action = "webServlet" method = "post">
	<div class = "flexbox">
	        <div class = "searchnav">Account Number Search
	            <input type="number" placeholder="Enter Account Number" name="accountId">
	            <br>
	            <button type="submit">Search</button>
	        </div>
	</div>
</form>

<script src="./updatecus.js"></script>
</body>
</html>