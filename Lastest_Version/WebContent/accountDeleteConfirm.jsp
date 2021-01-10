<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ABC Bank</title>
</head>
<body>

<% session.setAttribute("value", "accountDeleteConfirm"); %>

<h3>Check account info below and click confirm to either deactivate or delete account:</h3>
<div>ID: <%= session.getAttribute("accountDeleteID") %></div>
<div>Type: <%= session.getAttribute("accountDeleteType") %></div>
<div>Status: <%= session.getAttribute("accountDeleteStatus") %></div>
<div>Balance: <%= session.getAttribute("accountDeleteBalance") %></div>

<form action = "webServlet" method = "post">
	<button type="submit" name = "but" value = "delete">Confirm Delete</button>
</form>
<div>----------------------------------</div>
<form action = "webServlet" method = "post">
	<button type="submit" name = "but" value = "inactive">Confirm Inactive</button>
</form>


</body>
</html>