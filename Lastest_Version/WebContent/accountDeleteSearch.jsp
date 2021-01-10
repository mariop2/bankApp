<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ABC Bank</title>
</head>
<body>
<% session.setAttribute("value", "accountDeleteSearch"); %>

<form action = "webServlet" method = "post">
	<div class = "flexbox">
	        <div class = "searchnav">AccountID Search
	            <input type="number" placeholder="Enter Account ID" name="accid">
	            <button type="submit">Search</button>
	        </div>
	</div>
</form>
</body>
</html>