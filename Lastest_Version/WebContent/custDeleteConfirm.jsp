<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Delete</title>
</head>
<body>
<% session.setAttribute("value", "custDeleteConfirm"); %>

<h3>Check customer info below and click confirm to delete customer:</h3>
<div>ID: <%= session.getAttribute("custDeleteID") %></div>
<div>Name: <%= session.getAttribute("custDeleteName") %></div>
<div>Residence: <%= session.getAttribute("custDeleteCity") %>, <%= session.getAttribute("custDeleteState") %></div>

<form action = "webServlet" method = "post">
	<button type="submit" name = "sub" value = "delete">Confirm Delete</button>
</form>
<div>----------------------------------</div>
<form action = "webServlet" method = "post">
	<button type="submit" name = "sub" value = "inactive">Confirm Inactive</button>
</form>

</body>
</html>